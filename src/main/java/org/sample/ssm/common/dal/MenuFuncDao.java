package org.sample.ssm.common.dal;


import org.sample.ssm.web.vo.MenuFunc;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuFuncDao extends BasicDao {

    public MenuFunc findByUK(@Param("menuId") Long menuId, @Param("code") String code);

}
