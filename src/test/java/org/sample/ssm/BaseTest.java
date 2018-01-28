package org.sample.ssm;

import org.junit.runner.RunWith;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * 基础测试类。
 *
 * @author Yang Cheng
 * @version v 0.1 2018-01-28 18:28
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "/spring.xml", "file:src/main/webapp/WEB-INF/springmvc-servlet.xml" })
@ActiveProfiles({ "dev" })
// 标明是web应用测试，默认此目录
@WebAppConfiguration(value = "src/main/webapp")
public class BaseTest {
}
