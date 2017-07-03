/*
 * renren-toro-waltz-web - com.renren.toro.waltz.common.exception.support.WaltzExceptionLogHandler.java
 * 2015年5月19日:上午11:45:40
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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.PropertyAccessException;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理类,只负责记录日志 <br>
 */
@Component
public class ExceptionLogHandler implements HandlerExceptionResolver, Ordered {
    private final static Logger logger = LogManager.getLogger(ExceptionLogHandler.class);

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {
        logger.error("Path:{}", request.getServletPath());
        if (ex instanceof IllegalArgumentException || ex instanceof PropertyAccessException ||
            ex instanceof HttpRequestMethodNotSupportedException) {
            logger.error("{}", ex.getMessage());
        } else {
            logger.error("", ex);
        }

        // 返回null,会交给handler链上的下一个handlr,直到找到对应的handler
        return null;
    }

    @Override
    public int getOrder() {
        return HandlerOrder.get(getClass());
    }
}
