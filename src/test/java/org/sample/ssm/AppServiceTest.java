package org.sample.ssm;

import org.junit.Test;
import org.sample.ssm.biz.service.AppInfoService;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * description
 *
 * @author Yang Cheng
 * @version v 0.1 2018-01-28 18:28
 */
public class AppServiceTest extends BaseTest {
    @Autowired
    private AppInfoService appInfoService;

    @Test
    public void selectAllAppTest() {
        appInfoService.getApps().forEach(appDO -> {
            System.out.println(appDO);
        });
    }
}
