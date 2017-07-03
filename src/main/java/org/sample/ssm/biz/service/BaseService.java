package org.sample.ssm.biz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service 基类, 可在此提供Service层的公共方法，如获取序列号、生成主键等
 */
public abstract class BaseService {

    protected Logger logger = LoggerFactory.getLogger(this.getClass());

}
