package com.github.vita.ssm.common.dal;

import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MenuDao extends BasicDao {

    public int deleteByIds(List<Long> menuIds);

    public List findSubMenus(@Param("userId") Long userId, @Param("menuId") Long menuId);


}


