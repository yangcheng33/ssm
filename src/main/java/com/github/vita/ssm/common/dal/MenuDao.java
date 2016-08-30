package com.github.vita.ssm.auth.dao;

import com.github.walker.basewf.auth.vo.Menu;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao extends BasicDao {

    public int deleteByIds(List<Long> menuIds);

    public List findSubMenus(@Param("userId") Long userId, @Param("menuId") Long menuId);

    public Menu findByUK(@Param("code") String code);

}


