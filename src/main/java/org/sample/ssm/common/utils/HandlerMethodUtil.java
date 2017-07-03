/*
 * renren-toro-waltz-web - com.renren.toro.waltz.common.util.HandlerMethodUtils.java
 * 2015年5月8日:下午3:21:07
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
package org.sample.ssm.common.utils;

import org.springframework.web.method.HandlerMethod;

import java.lang.reflect.Method;

/**
 * spring mvc handleMehtod 工具 <br>
 *
 */
public final class HandlerMethodUtil {
    /**
     * 拦截器中使用,获得handler <br>
     * <br>
     *
     * @param handler
     * @return
     */
    public static final HandlerMethod getHandleMethod(
            Object handler) {
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            return handlerMethod;
        }
        return null;
    }

    /**
     * 获得处理请求的控制器 <br>
     * <br>
     *
     * @param handlerMethod
     * @return
     */
    public static final Method getHanderMethodExecutor(
            HandlerMethod handlerMethod) {
        Method method = handlerMethod.getMethod();
        return method;
    }
}
