package org.sample.ssm.web.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * MVC Controller基类，可在此提供Controller层的公共方法，如果上传、下载、参数转换等
 */
public abstract class BaseController {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

}
