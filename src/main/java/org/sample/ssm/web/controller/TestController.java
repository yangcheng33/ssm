package org.sample.ssm.web.controller;

import org.sample.ssm.biz.service.AppInfoService;
import org.sample.ssm.common.exception.BadRequestException;
import org.sample.ssm.common.po.AppDO;
import org.sample.ssm.web.converter.SsmConverterBinder;
import org.sample.ssm.web.form.TestForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 测试通用方法.
 *
 * @author Yang Cheng
 * @version v 0.1 2017-07-03 10:36
 */
@Controller
public class TestController extends BaseController {
    @Autowired
    AppInfoService appInfoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SsmConverterBinder.bindParam(binder);
    }

    @RequestMapping(value = "apps", method = RequestMethod.GET)
    @ResponseBody
    public List<AppDO> getApps() {
        // GET http://localhost:8080/ssm/apps
        return appInfoService.getApps();
    }

    @RequestMapping(value = "test", method = RequestMethod.POST)
    @ResponseBody
    public TestForm test(@RequestBody TestForm testForm) {
        // POST http://localhost:8080/ssm/test
        // content-type: application/json
        // request body: {'name':'a'}
        Assert.notNull(testForm.getName(), "name id required");
        return testForm;
    }

    @RequestMapping(value = "test2", method = RequestMethod.PUT)
    @ResponseBody
    public Integer test2(@RequestParam Integer value) {
        // PUT http://localhost:8080/ssm/test2?value=5
        return value;
    }

    @RequestMapping(value = "test3/{name}/{value}", method = RequestMethod.GET)
    @ResponseBody
    public String test3(@PathVariable String name, @PathVariable Integer value) {
        // GET http://localhost:8080/ssm/test3/a/5
        return name + " " + value;
    }

    @RequestMapping(value = "ex")
    @ResponseBody
    public String ex(@RequestParam Integer value, String name) {
        throw new BadRequestException();
    }
}