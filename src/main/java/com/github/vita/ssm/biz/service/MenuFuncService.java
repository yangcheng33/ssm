package com.github.vita.ssm.biz.service;

import com.github.miemiedev.mybatis.paginator.domain.PageBounds;
import com.github.vita.ssm.biz.cache.MenuFuncCache;
import com.github.vita.ssm.common.dal.MenuFuncDao;
import com.github.vita.ssm.common.utils.DateTimeUtil;
import com.github.vita.ssm.common.vo.MenuFunc;
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
    private MenuFuncCache menuFuncCache;


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

        PageBounds pageBounds = new PageBounds();
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
    }

}