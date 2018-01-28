package org.sample.ssm.common.dal;

import org.sample.ssm.common.po.AppDO;
import org.springframework.stereotype.Repository;
import tk.mybatis.mapper.common.Mapper;

@Repository
public interface AppDOMapper extends Mapper<AppDO> {
}