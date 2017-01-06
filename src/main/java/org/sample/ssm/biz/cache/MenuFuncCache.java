package org.sample.ssm.biz.cache;

import org.sample.ssm.biz.service.MenuService;
import org.sample.ssm.web.vo.MenuFunc;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 加载功能项并缓存
 * <p/>
 * Created by HuQingmiao on 2015-11-20.
 */
@Component
public class MenuFuncCache {

    protected final static Logger log = LoggerFactory.getLogger(MenuFuncCache.class);

    // Map<菜单ID, List<MenuFunc>>
    private final static Map<Long, List<MenuFunc>> menuFuncMap = new ConcurrentHashMap<Long, List<MenuFunc>>();

    @Autowired
    private MenuService menuService;

    public void load() throws Exception {
        log.info("[BEGIN]从表menu_func加载功能项...");
        try {
            List<MenuFunc> menuFuncList = menuService.findUserMenus(null);
            log.info("menuFuncList.size():" + menuFuncList.size());

            for (MenuFunc mf : menuFuncList) {
                if (menuFuncMap.containsKey(mf.getMenuId())) {
                    List<MenuFunc> list = menuFuncMap.get(mf.getMenuId());
                    list.add(mf);
                } else {
                    List<MenuFunc> list = new ArrayList<MenuFunc>();
                    list.add(mf);
                    menuFuncMap.put(mf.getMenuId(), list);
                }
            }

        } catch (Exception e) {
            log.error("[ERR]从表menu_func加载功能项出错!", e);
            throw e;
        }
        log.info("[END]从表menu_func加载功能项成功!");
    }


    public static List<MenuFunc> getFuncList(Long menuId) {
        if(!menuFuncMap.containsKey(menuId)){
            return new ArrayList<MenuFunc>(0);
        }
        return menuFuncMap.get(menuId);
    }


    public synchronized void refresh() throws Exception {
        menuFuncMap.clear();
        this.load();
    }
}
