package org.sample.ssm;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * test
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring.xml", "file:src/main/webapp/WEB-INF/springmvc-servlet.xml" })
// 标明是web应用测试
@WebAppConfiguration(value = "src/main/webapp") //可以不填，默认此目录
public class TestService {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Test
    public void testFindCanViewAll() throws Exception {

        List<Map<String, Object>> mList = null;
        for (Map<String, Object> m : mList) {
            System.out.println(">>");
            for (Iterator<String> it = m.keySet().iterator(); it.hasNext(); ) {
                String colName = it.next();
                System.out.println(">" + colName + ": " + m.get(colName).toString());
            }
        }
    }

}