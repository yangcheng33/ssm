package com.github.walker.basewf;

import com.github.walker.basewf.auth.dao.MenuDao;
import com.github.walker.basewf.auth.service.MenuService;
import com.github.walker.basewf.auth.service.UserService;

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

    @Autowired
    UserService userService;

    @Autowired
    MenuDao menuDao;

    @Autowired
    UserDao userDao;

    @Test
    public void testFindCanViewAll() throws Exception{

        List<Map<String, Object>> mList = userDao.findUserProds();
        for (Map<String, Object> m : mList) {
            System.out.println(">>");
            for (Iterator<String> it = m.keySet().iterator(); it.hasNext(); ) {
                String colName = it.next();
                System.out.println(">"+colName+": "+m.get(colName).toString());
            }
        }
    }


    @Test
    public void testFindUserMenuFuncs() throws Exception{

        Map<String, Map<String, Set<String>>> userMenuFuncsMap = userService.findUserMenuFuncs();

        for (Iterator<String> it = userMenuFuncsMap.keySet().iterator();it.hasNext();) {
            String acctNo= it.next();
            log.info("\n>>>"+acctNo);

            Map<String, Set<String>> menuFuncMap = userMenuFuncsMap.get(acctNo);

            for (Iterator<String> it2 = menuFuncMap.keySet().iterator(); it2.hasNext(); ) {
                String menuCode = it2.next();
                System.out.println(menuCode+": "+ menuFuncMap.get(menuCode).toString());
            }
        }
    }


    @Test
    public void testFindUserProds() throws Exception{

        Map<String, LinkedHashMap<String, String>> userProdsMap = userService.findUserProds();

        for (Iterator<String> it = userProdsMap.keySet().iterator();it.hasNext();) {
            String acctNo= it.next();
            log.info("\n>>>"+acctNo);

            Map<String, String> prodCodeNameMap = userProdsMap.get(acctNo);

            for (Iterator<String> it2 = prodCodeNameMap.keySet().iterator(); it2.hasNext(); ) {
                String prodCode = it2.next();
                System.out.println(prodCode+": "+ prodCodeNameMap.get(prodCode).toString());
            }
        }
    }

    @Test
    public void testFindCanViewAllUser() throws Exception{
        Set<String> userSet = userService.findCanViewAllUser();
        System.out.println(userSet.toString());
    }
}