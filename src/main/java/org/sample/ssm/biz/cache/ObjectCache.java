package org.sample.ssm.biz.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * 加载并缓存
 */
@Component
public class ObjectCache {

    private static List<Object> lists  = new ArrayList<>();
    private        Logger       logger = LoggerFactory.getLogger(this.getClass());

    /**
     * 加载缓存.
     */
    @PostConstruct
    public void loadPermission() {

    }

}
