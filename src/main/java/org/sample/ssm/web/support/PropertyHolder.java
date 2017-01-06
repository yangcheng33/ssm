package org.sample.ssm.web.support;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 存储spring读取到的property信息,用于后续使用
 */
public class PropertyHolder extends PropertyPlaceholderConfigurer {

    private static Map<String, String> ctxPropertiesMap = new HashMap<String, String>();


    @Override
    protected void processProperties(ConfigurableListableBeanFactory beanFactory,
                                     Properties props) throws BeansException {
        super.processProperties(beanFactory, props);
        //load properties to ctxPropertiesMap
        for (Object key : props.keySet()) {
            String keyStr = (String)key;
            String value = props.getProperty(keyStr);
            ctxPropertiesMap.put(keyStr, value);
        }
    }

    public static String getProperty(String name) {
        return ctxPropertiesMap.get(name);
    }
}