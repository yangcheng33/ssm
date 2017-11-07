package org.sample.ssm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 加载spring配置的测试用例。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring.xml", "file:src/main/webapp/WEB-INF/springmvc-servlet.xml" })
@ActiveProfiles({ "dev" })
// 标明是web应用测试，默认此目录
@WebAppConfiguration(value = "src/main/webapp")
public class ApplicationLoadTest {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void test() throws Exception {
    }

}