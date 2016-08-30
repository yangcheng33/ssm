package com.github.vita.ssm.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * 演示从页面各种输入栏取值及回显。
 * <p/>
 * Created by HuQingmiao on 2015/3/4 0004.
 */
@Controller
@RequestMapping(value = "tagsDemo")
public class TagsDemoCtl extends BasicController {

    @RequestMapping(value = "/prepare")
    public String prepareTest(Model model) {
        try {
            log.info(">>> prepareTest() : ");

            TagsDemoForm form = new TagsDemoForm();

            form.setInput("可以在这输入文本信息...");
            form.setPassword("在这输入密码，不要被别人看见了...");
            form.setTextArea("这里可以输入很多文本内容，GCD快完蛋了！");

            form.setCheckBoxBoolean(true);

            form.setCheckBoxArray1(new String[]{"item1", "item3", "item2"});

            form.setCheckBoxArray2(new String[]{"路人甲"});

            form.setCheckBoxList(Arrays.asList(1, 2));

            form.setRadiobuttonId(1);

            form.setSelectId1(3);

            form.setSelectId2(2);

            model.addAttribute("form", form);

            String[] preparedArray = new String[]{"路人甲", "路人乙", "路人丙"};
            model.addAttribute("preparedArray", preparedArray);

            LinkedHashMap<String, String> preparedMap = new LinkedHashMap<String, String>();
            preparedMap.put("1", "mapItem 路人甲");
            preparedMap.put("2", "mapItem 路人乙");
            preparedMap.put("3", "mapItem 路人丙");
            model.addAttribute("preparedMap", preparedMap);

            return "dtest/tagsDemo";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "dtest/tagsDemo";
        }
    }

    @RequestMapping(value = "/submit", method = {RequestMethod.POST})
    public String submitTest(@ModelAttribute("form") TagsDemoForm form, Map<String, Object> map) {
        try {
            log.info(">>> submitTest() : ");
            log.info("#: " + form.getInput());
            log.info("#: " + form.getSelectId3());

            String[] array1 = form.getCheckBoxArray1();
            log.info("CheckBoxArray1.size(): " + array1.length);
            map.put("form", form);

            String[] preparedArray = new String[]{"路人甲", "路人乙", "路人丙"};
            map.put("preparedArray", preparedArray);

            LinkedHashMap<String, String> preparedMap = new LinkedHashMap<String, String>();
            preparedMap.put("1", "mapItem 路人甲");
            preparedMap.put("2", "mapItem 路人乙");
            preparedMap.put("3", "mapItem 路人丙");
            map.put("preparedMap", preparedMap);

            return "dtest/tagsDemo";
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return "dtest/tagsDemo";
        }
    }
}