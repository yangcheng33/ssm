package org.sample.ssm.biz.service;

import org.sample.ssm.common.dal.AppDOMapper;
import org.sample.ssm.common.po.AppDO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 基本信息服务类.
 *
 * @author Yang Cheng
 * @version v 0.1 2017-07-03 11:54
 */
@Service
public class CommonInfoService extends BaseService {

    @Autowired
    private AppDOMapper appDOMapper;

    public List<AppDO> getApps() {
        return appDOMapper.selectAllApp();
    }

}
