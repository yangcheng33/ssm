package com.github.vita.ssm;


import com.github.vita.ssm.biz.service.MenuService;
import com.github.vita.ssm.common.dal.MenuDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import java.util.*;


/**
 * @author HuQingmiao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:spring.xml"})
@TransactionConfiguration(defaultRollback = false)
public class TestService {

    private Logger log = LoggerFactory.getLogger(this.getClass());


    @Autowired
    MenuService menuService;

    @Test
    public void testFindCanViewAll() throws Exception{

        List<Map<String, Object>> mList = null;
        for (Map<String, Object> m : mList) {
            System.out.println(">>");
            for (Iterator<String> it = m.keySet().iterator(); it.hasNext(); ) {
                String colName = it.next();
                System.out.println(">"+colName+": "+m.get(colName).toString());
            }
        }
    }


}