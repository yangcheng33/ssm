package org.sample.ssm.biz.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import org.sample.ssm.common.dal.MenuDao;
import org.sample.ssm.web.vo.Menu;
import org.sample.ssm.common.dal.MenuFuncDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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


}

