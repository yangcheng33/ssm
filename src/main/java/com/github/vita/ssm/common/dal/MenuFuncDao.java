package com.github.vita.ssm.common.dal;


import com.github.vita.ssm.web.vo.MenuFunc;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuFuncDao extends BasicDao {

    public MenuFunc findByUK(@Param("menuId") Long menuId, @Param("code") String code);

}
