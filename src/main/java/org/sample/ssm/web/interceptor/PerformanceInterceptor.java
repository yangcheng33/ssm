package org.sample.ssm.web.interceptor;

import org.sample.ssm.common.utils.HandlerMethodUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * 性能统计.
 */
public class PerformanceInterceptor implements HandlerInterceptor {
    private static Logger logger = LoggerFactory.getLogger(PerformanceInterceptor.class);

    private ThreadLocal<Long> startTimeThreadLocal = new ThreadLocal<Long>();

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception {

        HandlerMethod handlerMethod = HandlerMethodUtil.getHandleMethod(handler);
        if (handlerMethod != null) {
            startTimeThreadLocal.set(System.currentTimeMillis());
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
        throws Exception {

        HandlerMethod handlerMethod = HandlerMethodUtil.getHandleMethod(handler);
        if (handlerMethod != null) {
            Method method = handlerMethod.getMethod();

            CountTime annotation = method.getAnnotation(CountTime.class);
            if (annotation == null) {
                annotation = handlerMethod.getBeanType().getAnnotation(CountTime.class);
            }

            if (annotation != null) {
                printCostInfo(handlerMethod, annotation.forcePrint(), annotation.maxMilles());
            } else {
                printCostInfo(handlerMethod, false, 5000);
            }
        }

    }

    /**
     * 打印时间统计信息 <br>
     * <br>
     *
     * @param handlerMethod
     * @param forcePrint    强制打印
     * @param maxMilles     最大响应时间(毫秒)
     */
    private void printCostInfo(HandlerMethod handlerMethod, boolean forcePrint, int maxMilles) {
        long startTime = startTimeThreadLocal.get();
        long cost = System.currentTimeMillis() - startTime;

        StringBuilder sb = new StringBuilder();
        Method executor = HandlerMethodUtil.getHanderMethodExecutor(handlerMethod);

        boolean outofTime = cost > maxMilles;
        if (forcePrint || outofTime) {

            sb.append(executor.toString());
            sb.append(" cost:\t");
            sb.append(cost / 1000.0);
            sb.append("s");

            if (outofTime) {
                logger.warn(sb.toString());
            } else {
                logger.debug(sb.toString());
            }
        }
    }
}
