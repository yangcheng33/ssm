package org.sample.ssm.web.controller;

import org.sample.ssm.biz.service.CommonInfoService;
import org.sample.ssm.common.constant.MessageConst;
import org.sample.ssm.common.utils.ResponseUtil;
import org.sample.ssm.web.converter.SsmConverterBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;

/**
 * 基础信息控制类.
 *
 * @author Yang Cheng
 * @version v 0.1 2017-07-03 10:36
 */
@Controller
public class CommonInfoController extends BaseController {
    @Autowired
    CommonInfoService commonInfoService;

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SsmConverterBinder.bindParam(binder);
    }

    @RequestMapping(value = "getApp", method = RequestMethod.GET,
                    produces = MessageConst.JSON_FORMAT)
    @ResponseBody
    public Map getApp(@RequestParam Long id) {
        return ResponseUtil.returnOK(commonInfoService.getApp(id));
    }

}