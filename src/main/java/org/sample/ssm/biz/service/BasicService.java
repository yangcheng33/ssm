package org.sample.ssm.biz.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service 基类, 可在此提供Service层的公共方法，如获取序列号、生成主键等
 * <p/>
 * Created by Huqingmiao on 2015-5-16.
 */
public abstract class BasicService {

    protected Logger log = LoggerFactory.getLogger(this.getClass());

}
