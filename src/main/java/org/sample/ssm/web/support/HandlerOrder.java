/*
 * renren-toro-waltz-web - com.renren.toro.waltz.common.exception.support.HandlerOrder.java
 * 2015年5月21日:下午1:48:35
 * Keen
 *
 * jacks808@163.com
 *
 * KKKKKKKKK    KKKKKKK                                                          
 * K:::::::K    K:::::K                                                          
 * K:::::::K    K:::::K                                                          
 * K:::::::K   K::::::K                                                          
 * KK::::::K  K:::::KKK    eeeeeeeeeeee        eeeeeeeeeeee    nnnn  nnnnnnnn    
 *   K:::::K K:::::K     ee::::::::::::ee    ee::::::::::::ee  n:::nn::::::::nn  
 *   K::::::K:::::K     e::::::eeeee:::::ee e::::::eeeee:::::een::::::::::::::nn 
 *   K:::::::::::K     e::::::e     e:::::ee::::::e     e:::::enn:::::::::::::::n
 *   K:::::::::::K     e:::::::eeeee::::::ee:::::::eeeee::::::e  n:::::nnnn:::::n
 *   K::::::K:::::K    e:::::::::::::::::e e:::::::::::::::::e   n::::n    n::::n
 *   K:::::K K:::::K   e::::::eeeeeeeeeee  e::::::eeeeeeeeeee    n::::n    n::::n
 * KK::::::K  K:::::KKKe:::::::e           e:::::::e             n::::n    n::::n
 * K:::::::K   K::::::Ke::::::::e          e::::::::e            n::::n    n::::n
 * K:::::::K    K:::::K e::::::::eeeeeeee   e::::::::eeeeeeee    n::::n    n::::n
 * K:::::::K    K:::::K  ee:::::::::::::e    ee:::::::::::::e    n::::n    n::::n
 * KKKKKKKKK    KKKKKKK    eeeeeeeeeeeeee      eeeeeeeeeeeeee    nnnnnn    nnnnnn
 * 
 */
package org.sample.ssm.web.support;

import com.google.common.collect.Lists;
import org.springframework.core.Ordered;

import java.util.List;

/**
 * handler 顺序 <br>
 */
public final class HandlerOrder {
    private final static List<Class<? extends Ordered>> handlerOrderList = Lists.newArrayList();

    static {
        handlerOrderList.add(ExceptionLogHandler.class);
        handlerOrderList.add(ExceptionJsonHandler.class);
    }

    /**
     * 获得处理器类的顺序 <br>
     * 2015年5月21日:下午1:53:24<br>
     * <br>
     *
     * @param orderedClass
     */
    public static final int get(Class<? extends Ordered> orderedClass) {
        return handlerOrderList.indexOf(orderedClass);
    }
}
