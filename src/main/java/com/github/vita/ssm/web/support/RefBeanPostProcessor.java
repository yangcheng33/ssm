package com.github.walker.common.support;

import com.github.walker.common.cache.MenuFuncCache;
import com.github.walker.common.cache.UserAuthCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * 增加Spring加载Bean前后的调用逻辑
 * <p/>
 * Created by HuQingmiao
 */
public class RefBeanPostProcessor implements BeanPostProcessor {

    private Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public Object postProcessBeforeInitialization(Object arg0, String arg1) throws BeansException {
        return arg0;
    }

    @Override
    public Object postProcessAfterInitialization(Object arg0, String arg1) throws BeansException {
        try {
//            log.info("<<<" + arg0.getClass().getName() + "     " + arg1);
//            if (arg0 instanceof EventCaller) {
//                new Thread((EventCaller) arg0).start();
//            }

            if (arg0 instanceof MenuFuncCache) {
                ((MenuFuncCache) arg0).load();
            }
            if (arg0 instanceof UserAuthCache) {
                ((UserAuthCache) arg0).load();
            }

        } catch (Exception e) {
            log.error(e.getMessage(), e);
        }
        return arg0;
    }

}
