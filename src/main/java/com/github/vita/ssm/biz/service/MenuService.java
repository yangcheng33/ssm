package com.github.walker.basewf.auth.service;

import com.github.walker.basewf.auth.dao.MenuDao;
import com.github.walker.basewf.auth.vo.Menu;
import com.github.walker.basewf.auth.vo.MenuFunc;
import com.github.vita.ssm.auth.biz.BasicService;
import com.github.walker.common.cache.MenuFuncCache;
import com.github.walker.common.cache.UserAuthCache;
import com.github.walker.common.utils.DateTimeUtil;
import com.github.walker.mybatis.paginator.Order;
import com.github.walker.mybatis.paginator.PageBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.*;

/**
 * @author HuQingmiao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuService extends BasicService {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    private MenuFuncDao menuFuncDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private UserAuthCache userAuthCache;


    /**
     * 获取指定用户拥有的某菜单的子菜单
     *
     * @param userId 用户ID号
     * @param menuId 菜单ID号
     * @throws Exception
     */
    public List<Menu> findSubMenus(Long userId, Long menuId) throws Exception {
        List<Menu> menuList = menuDao.findSubMenus(userId, menuId);
        return menuList;
    }


    public List<Menu> findSubMenus(Long menuId) throws Exception {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        if (menuId != null) {
            paramMap.put("pid", menuId);
        }
        return menuDao.find(paramMap, new PageBounds());
    }


    /**
     * 根据 菜单名称, 父菜单名称 分页查找
     *
     * @param menuName
     * @param offset
     * @param rowCnt
     * @return
     * @throws Exception
     */
    public List findMenus(String menuName,String pMenuName, int offset, int rowCnt)
            throws Exception {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        if (menuName != null && !"".equals(menuName)) {
            paramMap.put("menuName", menuName + "%");
        }
        if (pMenuName != null && !"".equals(pMenuName)) {
            paramMap.put("pname", pMenuName + "%");
        }

        PageBounds pageBounds = new PageBounds(offset, rowCnt, Order.formString("pname.asc, name.asc"));
        return menuDao.find(paramMap, pageBounds);
    }

    /**
     * 根据 菜单名称, 父菜单名称
     *
     * @param menuName
     * @return
     * @throws Exception
     */
    public List<Menu> findMenus(String menuName) throws Exception {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        if (menuName != null && !"".equals(menuName)) {
            paramMap.put("menuName", menuName + "%");
        }

        PageBounds pageBounds = new PageBounds(Order.formString("name.asc"));
        return menuDao.find(paramMap, pageBounds);
    }

    /**
     * 获取菜单ID与菜单编号的映射Map
     * @return
     * @throws Exception
     */
    public Map<Long,String> retrieveIdCodeMap() throws Exception{
        List<Menu> menuList = this.findMenus("");
        Map<Long,String> menuIdCodeMap = new HashMap<Long, String>();
        for(Menu menu:menuList){
            //log.info(">>"+menu.getId()+menu.getCode());
            menuIdCodeMap.put(menu.getId(),menu.getCode());
        }
        return menuIdCodeMap;
    }


    /**
     * 获取某用户拥有的所有菜单
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public List findUserMenus(Long userId) throws Exception {

        //取该用户拥有的顶级菜单, 父菜单ID号为0的表示顶级
        List menus = this.findSubMenus(userId, new Long("0"));

        //取各顶级菜单下的子菜单
        for (int i = 0; i < menus.size(); i++) {
            Menu menu = (Menu) menus.get(i);
            List subMenus = this.findSubMenus(userId, menu.getId());
            menu.setSubMenus(subMenus);
        }

        return menus;
    }


    public Menu findById(Long menuId) throws Exception {
        return (Menu) menuDao.findByPK(menuId);
    }

    public Menu findByCode(String code) throws Exception {
        return menuDao.findByUK(code);
    }

    public void addMenu(Menu menu) throws Exception {
        Timestamp currTime = DateTimeUtil.currentTime();

        menu.setCreateTime(currTime);
        menuDao.save(menu);
    }

    public void updateMenu(Menu menu) throws Exception {
        Timestamp currTime = DateTimeUtil.currentTime();

        menu.setUpdateTime(currTime);
        menuDao.update(menu);

        // 更新缓存
        userAuthCache.refreshUserMenuFuncCache();
    }


    /**
     * 删除菜单及与角色的关联关系
     *
     * @throws Exception
     */
    public void deleteMenu(Long menuId) throws Exception {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("menuId", menuId);

        List<Long> subMenuIdList = new ArrayList<Long>();

        // 根据该菜单ID取得其下子菜单
        List subMenuList = this.findSubMenus(menuId);
        for (int i = 0; i < subMenuList.size(); i++) {
            Menu subMenu = (Menu) subMenuList.get(i);
            subMenuIdList.add(subMenu.getId());
        }

        // 删除与子菜单相关的角色关系
        if (!subMenuIdList.isEmpty()) {
            roleMenuDao.deleteByMenuIds(subMenuIdList);
        }

        // 删除与本菜单相关的角色关系
        roleMenuDao.deleteByMenuId(menuId);

        // 删除子菜单
        if (!subMenuIdList.isEmpty()) {
            menuDao.deleteByIds(subMenuIdList);
        }

        // 删除本菜单功能项
        List<MenuFunc> funcList = MenuFuncCache.getFuncList(menuId);
        if (!funcList.isEmpty()) {
            menuFuncDao.deleteBatch(funcList);
        }

        // 删除本菜单
        menuDao.deleteByPK(menuId);

        // 更新缓存
        userAuthCache.refreshUserMenuFuncCache();
    }

}

