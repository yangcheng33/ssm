package com.github.walker.basewf.auth.service;

import com.github.walker.basewf.auth.vo.User;
import com.github.walker.basewf.auth.vo.UserRole;
import com.github.vita.ssm.auth.biz.BasicService;
import com.github.walker.common.cache.UserAuthCache;
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
 * 用户管理业务类
 *
 * @author HuQingmiao
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends BasicService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private UserRoleDao userRoleDao;

    @Autowired
    private MenuService menuService;

    @Autowired
    private MenuFuncService menuFuncService;

    @Autowired
    private UserAuthCache userAuthCache;

    /**
     * 根据用户名、真实姓名近似查找用户资料
     *
     * @param acctNo 账号
     * @param name   姓名
     * @return
     * @throws Exception
     */
    public List<User> findUsers(String acctNo, String name, int offset, int rowCnt)
            throws Exception {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        if (acctNo != null && !"".equals(acctNo)) {
            paramMap.put("acctNo", acctNo + "%");
        }
        if (name != null && !"".equals(name)) {
            paramMap.put("name", name + "%");
        }

        PageBounds pageBounds = new PageBounds(offset, rowCnt, Order.formString("name.asc"));
        return userDao.find(paramMap, pageBounds);
    }


    /**
     * 根据用户名、真实姓名近似查找用户资料
     *
     * @param acctNo 账号
     * @param name   姓名
     * @return
     * @throws Exception
     */
    public User findUser(String acctNo, String name) throws Exception {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        if (acctNo != null) {
            paramMap.put("acctNo", acctNo);
        }
        if (name != null && !"".equals(name.trim())) {
            paramMap.put("name", name);
        }

        PageBounds pageBounds = new PageBounds(Order.formString("name.asc"));
        List<User> userList = userDao.find(paramMap, pageBounds);

        if (!userList.isEmpty()) {
            return userList.get(0);
        }
        return null;

    }


    public User findById(Long userId) throws Exception {
        return (User) userDao.findByPK(userId);
    }

    public User findByAcctNo(String acctNo) throws Exception {
        return userDao.findByUK(acctNo);
    }

    public void addUser(User user) throws Exception {
        user.setCreateTime(DateTimeUtil.currentTime());
        userDao.save(user);
    }

    public void updateUser(User user) throws Exception {
        user.setUpdateTime(DateTimeUtil.currentTime());
        userDao.update(user);
    }

    /**
     * 删除用户, 及其与角色的关联关系
     *
     * @param userId 用户ID号
     * @throws Exception
     */
    public void deleteUser(Long userId) throws Exception {

        // 删除与角色的关联关系
        List<UserRole> userRoleList = this.findUserRoles(userId);
        if (!userRoleList.isEmpty()) {
            userRoleDao.deleteBatch(userRoleList);
        }

        // 再删除用户
        userDao.deleteByPK(userId);

        // 刷新缓存
        userAuthCache.refreshUserMenuFuncCache();
        userAuthCache.refreshUserProdsCache();
        userAuthCache.refreshCanViewAllCache();
    }


    /**
     * 找出 某用户关联的角色
     *
     * @param userId
     * @return
     * @throws Exception
     */
    public List<UserRole> findUserRoles(Long userId) throws Exception {

        HashMap<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);

        // 删除与角色的关联关系
        List<UserRole> userRoleList = userRoleDao.find(paramMap, new PageBounds());
        return userRoleList;

    }

    /**
     * 重新给指定用户关联角色
     *
     * @param userId
     * @param roleIdArray
     * @throws Exception
     */
    public void resetUserRoles(Long userId, Long[] roleIdArray)
            throws Exception {

        Timestamp currTime = DateTimeUtil.currentTime();

        // 删除该用户当前拥有的角色
        userRoleDao.deleteByUserId(userId);

        List<UserRole> userRoleList = new ArrayList<UserRole>();
        for (int i = 0; i < roleIdArray.length; i++) {
            UserRole userRole = new UserRole();
            userRole.setUserId(userId);
            userRole.setRoleId(roleIdArray[i]);

            userRole.setCreateTime(currTime);

            userRoleList.add(userRole);
        }

        // 重新写入用户及角色关系
        if (!userRoleList.isEmpty()) {
            userRoleDao.saveBatch(userRoleList);
        }

        // 刷新缓存
        userAuthCache.refreshUserMenuFuncCache();
        userAuthCache.refreshUserProdsCache();
        userAuthCache.refreshCanViewAllCache();
    }


    /**
     * 查找各用户绑定的"菜单及功能项"
     *
     * @return Map<用户账号, Map<菜单编号, Set<功能编号>> >
     */
    public Map<String, Map<String, Set<String>>> findUserMenuFuncs() throws Exception {

        // Map<menuId, menuCode>
        Map<Long, String> menuIdCodeMap = menuService.retrieveIdCodeMap();

        // Map<'menuId.funcId', funcCode>
        Map<String, String> mfIdCodeMap = menuFuncService.retrieveIdCodeMap();

        // Map<acctNo, Map<menuCode, Set<funcCode>>>
        Map<String, Map<String, Set<String>>> userAuthMap = new HashMap<String, Map<String, Set<String>>>();

        List<Map<String, Object>>  kvMapList = userDao.findUserMenuFuncs();
        for (Map<String, Object> kvMap : kvMapList) {
            String acctNo = (String) kvMap.get("acct_no");
            Long menuId = (Long) kvMap.get("menu_id");
            String funcIds = (String) kvMap.get("func_ids");
            if (funcIds == null) {
                funcIds = "";
            }

            // 菜单/页面编号
            String menuCode = menuIdCodeMap.get(menuId);

            // 转换得到当前menuId对应的功能项编号
            HashSet<String> funcCodeSet = this.toFuncCodeSet(menuId, funcIds, mfIdCodeMap);

            if (userAuthMap.containsKey(acctNo)) {
                // Map<menuCode, Set<funcCode>>
                Map<String, Set<String>> menuFuncsMap = userAuthMap.get(acctNo);

                if (menuFuncsMap.containsKey(menuCode)) {
                    Set<String> existedFuncCodesSet = menuFuncsMap.get(menuCode);
                    existedFuncCodesSet.addAll(funcCodeSet);
                } else {
                    menuFuncsMap.put(menuCode, funcCodeSet);
                }

            } else {
                // Map<menuCode, Set<funcCode>>
                Map<String, Set<String>> menuFuncsMap = new HashMap<String, Set<String>>();
                userAuthMap.put(acctNo, menuFuncsMap);

                menuFuncsMap.put(menuCode, funcCodeSet);
            }
        }

        return userAuthMap;
    }


    /**
     * 查找各用户绑定的"产品列表"
     *
     * @return Map<用户账号, Map<产品编号, 产品名称>>
     */
    public Map<String, LinkedHashMap<String, String>> findUserProds() {

        // Map<acctNo, Map<prodCode, prodName>>
        Map<String, LinkedHashMap<String, String>> userProdMap = new HashMap<String, LinkedHashMap<String, String>>();

        List<Map<String, Object>> kvMapList = userDao.findUserProds();

        for (Map<String, Object> kvMap : kvMapList) {
            String acctNo = (String) kvMap.get("acct_no");
            String prodCode = (String) kvMap.get("prod_code");
            String prodName = (String) kvMap.get("prod_name");

            if (userProdMap.containsKey(acctNo)) {
                // Map<prodCode, prodName>
                Map<String, String> prodCodeNameMap = userProdMap.get(acctNo);
                prodCodeNameMap.put(prodCode, prodName);

            } else {
                // Map<prodCode, prodName>
                LinkedHashMap<String, String> prodCodeNameMap = new LinkedHashMap<String, String>();
                prodCodeNameMap.put(prodCode, prodName);

                userProdMap.put(acctNo, prodCodeNameMap);
            }
        }
        return userProdMap;
    }



    /**
     * 查找可查看所有业务数据的用户
     *
     * @return
     */
    public Set<String> findCanViewAllUser(){
        List<Map<String, Object>> kvMapList = userDao.findCanViewAllUser();

        Set<String> userSet = new HashSet<String>();
        for (Map<String, Object> kvMap : kvMapList) {
            String acctNo = (String) kvMap.get("acct_no");
            userSet.add(acctNo);
        }
        return userSet;
    }


    /**
     *
     * @param menuId
     * @param funcIds
     * @param mfIdCodeMap
     * @return
     * @throws Exception
     */
    private HashSet<String> toFuncCodeSet(Long menuId, String funcIds, Map<String, String> mfIdCodeMap)
            throws Exception {
        Set<String> funcIdSet = CommonUtil.toSet(funcIds, ",");
        HashSet<String> funcCodeSet = new HashSet<String>();
        for (Iterator<String> it = funcIdSet.iterator(); it.hasNext(); ) {
            String funcId = it.next();
            String mfId = menuId.toString() + "." + funcId;
            funcCodeSet.add(mfIdCodeMap.get(mfId));
        }
        funcIdSet.clear();

        return funcCodeSet;
    }
}
