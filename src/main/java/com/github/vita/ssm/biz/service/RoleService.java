package com.github.walker.basewf.auth.service;

import com.github.walker.basewf.auth.vo.Role;
import com.github.walker.basewf.auth.vo.RoleMenu;
import com.github.walker.basewf.auth.vo.RoleProd;
import com.github.walker.basewf.auth.vo.UserRole;
import com.github.vita.ssm.auth.biz.BasicService;
import com.github.walker.common.cache.UserAuthCache;
import com.github.walker.common.constant.Binary;
import com.github.walker.common.utils.CommonUtil;
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
public class RoleService extends BasicService {

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private RoleMenuDao roleMenuDao;

    @Autowired
    private RoleProdDao roleProdDao;

    @Autowired
    private RoleMenuDao userRoleDao;

    @Autowired
    private UserAuthCache userAuthCache;

    /**
     * 根据角色名称 分页查找
     *
     * @param roleName
     * @param offset
     * @param rowCnt
     * @return
     * @throws Exception
     */
    public List<Role> findRoles(String roleName, int offset, int rowCnt)
            throws Exception {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        if (roleName != null && !"".equals(roleName)) {
            paramMap.put("name", roleName + "%");
        }

        PageBounds pageBounds = new PageBounds(offset, rowCnt, Order.formString("name.asc"));
        return roleDao.find(paramMap, pageBounds);
    }

    /**
     * 根据角色名称查找
     *
     * @param roleName
     * @return
     * @throws Exception
     */
    public List<Role> findRoles(String roleName)
            throws Exception {
        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        if (roleName != null && !"".equals(roleName)) {
            paramMap.put("name", "%" + roleName + "%");
        }

        PageBounds pageBounds = new PageBounds(Order.formString("name.asc"));
        return roleDao.find(paramMap, pageBounds);
    }

    public Role findById(Long roleId) throws Exception {
        return (Role) roleDao.findByPK(roleId);
    }

    public Role findByName(String roleName) throws Exception {
        return roleDao.findByUK(roleName);
    }

    public void addRole(Role role) throws Exception {
        role.setCanViewAll(Binary.YES.getCode().shortValue());
        role.setCreateTime(DateTimeUtil.currentTime());
        roleDao.save(role);
    }

    public void updateRole(Role role) throws Exception {
        role.setUpdateTime(DateTimeUtil.currentTime());
        roleDao.update(role);
    }

    /**
     * 删除角色, 包括与用户的关联关系、与菜单的关联关系
     *
     * @param roleId 角色ID号
     * @throws Exception
     */
    public void deleteRole(Long roleId) throws Exception {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleId", roleId);

        // 删除与菜单的关联关系
        List<RoleMenu> roleMenuList = roleMenuDao.find(paramMap, new PageBounds());
        if (!roleMenuList.isEmpty()) {
            roleMenuDao.deleteBatch(roleMenuList);
        }

        // 删除与用户的关联关系
        List<UserRole> userRoleList = roleMenuDao.find(paramMap, new PageBounds());
        if (!userRoleList.isEmpty()) {
            userRoleDao.deleteBatch(userRoleList);
        }

        // 再删除角色
        roleDao.deleteByPK(roleId);

        // 刷新缓存
        userAuthCache.refreshUserMenuFuncCache();
        userAuthCache.refreshUserProdsCache();
        userAuthCache.refreshCanViewAllCache();
    }


    /**
     * 重新设置指定角色所拥有的菜单
     *
     * @throws Exception
     */
    public void resetRoleMenus(Long roleId, Long[] menuIds, Map<Long, Set<Long>> midFidsMap)
            throws Exception {

        Timestamp currTime = DateTimeUtil.currentTime();

        // 删除该角色当前的菜单
        roleMenuDao.deleteByRoleId(roleId);

        List<RoleMenu> roleMenuList = new ArrayList<RoleMenu>();

        for (Long menuId : menuIds) {
            Set<Long> funcIdSet = midFidsMap.get(menuId);
            RoleMenu roleMenu = new RoleMenu();
            roleMenu.setRoleId(roleId);
            roleMenu.setMenuId(menuId);
            if (funcIdSet != null) {
                roleMenu.setFuncIds(CommonUtil.toString(funcIdSet, ","));
            }
            roleMenu.setCreateTime(currTime);
            roleMenuList.add(roleMenu);
        }

        //添加本次为该角色所选的菜单
        if (!roleMenuList.isEmpty()) {
            roleMenuDao.saveBatch(roleMenuList);
        }

        // 刷新缓存
        userAuthCache.refreshUserMenuFuncCache();
    }


    public void resetRoleProds(Long roleId, Map<String, String> prodCodeNameMap, Short canViewAll)
            throws Exception {

        Timestamp currTime = DateTimeUtil.currentTime();

        // 删除该角色绑定的产品
        roleProdDao.deleteByRoleId(roleId);

        List<RoleProd> roleProdList = new ArrayList<RoleProd>();
        for (Iterator<String> it = prodCodeNameMap.keySet().iterator(); it.hasNext(); ) {
            String prodCode = it.next();
            String prodName = prodCodeNameMap.get(prodCode);
            RoleProd roleProd = new RoleProd();
            roleProd.setRoleId(roleId);
            roleProd.setProdCode(prodCode);
            roleProd.setProdName(prodName);
            roleProd.setCreateTime(currTime);
            roleProdList.add(roleProd);
        }

        //添加本次为该角色所选的菜单
        if (!roleProdList.isEmpty()) {
            roleProdDao.saveBatch(roleProdList);
        }

        //更新角色
        Role role = (Role) roleDao.findByPK(roleId);
        role.setCanViewAll(canViewAll);
        roleDao.update(role);

        // 刷新缓存
        userAuthCache.refreshUserProdsCache();
        userAuthCache.refreshCanViewAllCache();
    }

    /**
     * 找出 某角色所拥有的菜单
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<RoleMenu> findRoleMenus(Long roleId) throws Exception {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleId", roleId);

        return roleMenuDao.find(paramMap, new PageBounds());
    }


    /**
     * 找出某角色绑定的产品
     *
     * @param roleId
     * @return
     * @throws Exception
     */
    public List<RoleProd> findRoleProd(Long roleId) throws Exception {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("roleId", roleId);

        return roleProdDao.find(paramMap, new PageBounds());
    }
}

