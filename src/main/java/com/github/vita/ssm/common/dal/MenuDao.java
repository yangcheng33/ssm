package com.github.vita.ssm.common.dal;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MenuDao extends BasicDao {

    public int deleteByIds(List<Long> menuIds);

    public List findSubMenus(@Param("userId") Long userId, @Param("menuId") Long menuId);


}


