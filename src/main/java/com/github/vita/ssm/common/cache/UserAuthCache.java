package com.github.walker.common.cache;

import com.github.walker.basewf.auth.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;

/**
 * 加载用户权限并缓存
 * <p/>
 * Created by HuQingmiao on 2015-11-20.
 */
@Component
public class UserAuthCache {

    protected final static Logger log = LoggerFactory.getLogger(UserAuthCache.class);

    // 各用户绑定的"菜单及功能项": Map<用户账号, Map<菜单编号, Set<功能编号>> >
    private final static Map<String, Map<String, Set<String>>> userMenuFuncsMap =
            new ConcurrentHashMap<String, Map<String, Set<String>>>();

    // 各用户绑定的"产品列表": Map<用户账号, Map<产品编号, 产品名称>>
    private final static Map<String, LinkedHashMap<String, String>> userProdMap =
            new ConcurrentHashMap<String, LinkedHashMap<String, String>>();

    // 可查看所有业务数据的用户: Set<用户账号>
    private final static Set<String> canViewAllUserSet = new CopyOnWriteArraySet<String>();

    @Autowired
    private UserService userService;


    public void load() throws Exception {
        log.info("[BEGIN]加载用户的各项权限:");
        try {
            log.info("加载用户的功能项权限...");
            userMenuFuncsMap.putAll(userService.findUserMenuFuncs());

            log.info("加载用户的产品权限...");
            userProdMap.putAll(userService.findUserProds());

            log.info("加载用户的数据权限...");
            canViewAllUserSet.addAll(userService.findCanViewAllUser());

        } catch (Exception e) {
            log.error("[ERR]加载用户的各项权限失败:", e);
            throw e;
        }
        log.info("[END]加载用户的各项权限成功!");
    }



    /**
     * 根据用户账号和页面编号，获取用户在该页面的功能项。
     *
     * @param menuCode
     * @return 用户可使用的功能项：Set<功能项编号>
     */
    public Set<String> getMenuFuncSet(String acctNo, String menuCode){
        Map<String, Set<String>> menuFuncMap = userMenuFuncsMap.get(acctNo);
        Set<String> funcCodeSet =  menuFuncMap.get(menuCode);

        return funcCodeSet;
    }


    /**
     * 根据用户账号，获取该用户的可浏览的产品列表。
     *
     * @param acctNo
     * @return Map<产品码, 中文描述>
     */
    public LinkedHashMap<String, String> getProdList(String acctNo){
        // Map<产品编号, 产品名称>
        return userProdMap.get(acctNo);
    }


    /**
     * 判断指定用户是否有权限查看所有用户创建的营销活动、目标组、试发名单等。
     *
     * @param acctNo
     * @return
     */
    public boolean canViewAllData(String acctNo){
        return canViewAllUserSet.contains(acctNo);
    }



    public synchronized void refreshUserMenuFuncCache() throws Exception {
        log.info(">>刷新缓存：用户绑定的功能项列表");
        userMenuFuncsMap.clear();
        userMenuFuncsMap.putAll(userService.findUserMenuFuncs());
    }

    public synchronized void refreshUserProdsCache() throws Exception {
        log.info(">>刷新缓存：用户绑定的产品列表");
        userProdMap.clear();
        userProdMap.putAll(userService.findUserProds());
    }

    public synchronized void refreshCanViewAllCache() throws Exception {
        log.info(">>刷新缓存：可查看所有数据的用户");
        canViewAllUserSet.clear();
        canViewAllUserSet.addAll(userService.findCanViewAllUser());
    }
}
