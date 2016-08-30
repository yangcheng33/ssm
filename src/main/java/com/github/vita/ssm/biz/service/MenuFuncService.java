package com.github.walker.basewf.auth.service;

import com.github.walker.basewf.auth.vo.MenuFunc;
import com.github.walker.basewf.auth.vo.RoleMenu;
import com.github.vita.ssm.auth.biz.BasicService;
import com.github.walker.common.cache.MenuFuncCache;
import com.github.walker.common.cache.UserAuthCache;
import com.github.walker.common.utils.CommonUtil;
import com.github.walker.common.utils.DateTimeUtil;
import com.github.walker.mybatis.paginator.Order;
import com.github.walker.mybatis.paginator.PageBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author HuQingmiao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MenuFuncService extends BasicService {

    @Autowired
    private MenuFuncDao menuFuncDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private MenuFuncCache menuFuncCache;

    @Autowired
    private UserAuthCache userAuthCache;

    /**
     * 查找某个菜单下的功能项
     *
     * @param menuId
     * @return
     * @throws Exception
     */
    public List<MenuFunc> findFuncList(Long menuId)
            throws Exception {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        if (menuId != null) {
            paramMap.put("menuId", menuId);
        }

        PageBounds pageBounds = new PageBounds(Order.formString("name.asc"));
        return menuFuncDao.find(paramMap, pageBounds);
    }

    /**
     * 获取所有功能项的ID与编号的对应关系
     *
     * @return Map<'menuId.funcId', funcCode>
     * @throws Exception
     */
    public Map<String, String> retrieveIdCodeMap()
            throws Exception {
        List<MenuFunc> menuFuncList = this.findFuncList(null);
        Map<String, String> funcIdNameMap = new HashMap<String, String>();
        for (MenuFunc mf : menuFuncList) {
            //System.out.println(">>" + mf.toString());
            String key = mf.getMenuId().toString() + "." + mf.getId();
            funcIdNameMap.put(key, mf.getCode());
        }
        return funcIdNameMap;
    }


    public MenuFunc findById(Long funcId) throws Exception {
        return (MenuFunc) menuFuncDao.findByPK(funcId);
    }

    public MenuFunc findByUK(Long menuId, String code) throws Exception {
        return menuFuncDao.findByUK(menuId, code);
    }

    /**
     * 新增功能项
     *
     * @param menuFunc
     * @throws Exception
     */
    public void addMenuFunc(MenuFunc menuFunc) throws Exception {
        menuFunc.setCreateTime(DateTimeUtil.currentTime());
        menuFuncDao.save(menuFunc);

        // 更新缓存
        menuFuncCache.refresh();
    }


    /**
     * 更新功能项
     *
     * @param menuFunc
     * @throws Exception
     */
    public void updateMenuFunc(MenuFunc menuFunc) throws Exception {
        menuFunc.setUpdateTime(DateTimeUtil.currentTime());
        menuFuncDao.update(menuFunc);

        // 更新缓存
        menuFuncCache.refresh();
        userAuthCache.refreshUserMenuFuncCache();
    }

    /**
     * 删除菜单其下的功能项
     *
     * @param menuId
     * @param funcId
     * @throws Exception
     */
    public void deleteMenuFunc(Long menuId, Long funcId) throws Exception {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("menuId", menuId);

        // 找出涉及到本功能项的关联记录，对其更新
        List<RoleMenu> roleMenuList = roleMenuDao.find(paramMap, new PageBounds());
        for (RoleMenu roleMenu : roleMenuList) {
            String funcIds = roleMenu.getFuncIds();
            if (funcIds == null) {
                continue;
            }
            Set<String> funcIdSet = CommonUtil.toSet(funcIds, ",");
            if (funcIdSet.contains(funcId.toString())) {
                funcIdSet.remove(funcIds.toString());
            }
            roleMenu.setFuncIds(CommonUtil.toString(funcIdSet, ","));
            roleMenuDao.update(roleMenu);
        }

        // 删除功能项
        menuFuncDao.deleteByPK(funcId);

        // 更新缓存
        menuFuncCache.refresh();
        userAuthCache.refreshUserMenuFuncCache();
    }
}