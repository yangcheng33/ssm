/*
 * renren-toro-waltz-web - com.renren.toro.waltz.common.exception.support.WaltzExceptionMavHandler.java
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

import com.google.common.collect.Maps;
import org.apache.http.HttpStatus;
import org.sample.ssm.common.constant.ErrorInfo;
import org.sample.ssm.common.constant.MessageConst;
import org.sample.ssm.common.exception.WebRuntimeException;
import org.sample.ssm.common.utils.HandlerMethodUtil;
import org.springframework.core.Ordered;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * 异常处理类<br>
 */
@Component
public class ExceptionJsonHandler implements HandlerExceptionResolver, Ordered {

    @Override
    public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
                                         Exception ex) {

        HandlerMethod handleMethod = HandlerMethodUtil.getHandleMethod(handler);
        boolean isAddResponseBody =
            handleMethod == null || handleMethod.getMethodAnnotation(ResponseBody.class) != null;

        // json请求中的异常
        if (isAddResponseBody) {
            Map<String, Object> model = Maps.newHashMap();
            if (ex instanceof WebRuntimeException) {
                model.put(MessageConst.CODE, ((WebRuntimeException) ex).getCode());
                model.put(MessageConst.MSG, ex.getMessage());

            } else if (ex instanceof DuplicateKeyException) {
                model.put(MessageConst.CODE, ErrorInfo.DB_DATA_REPEAT.getCode());
                model.put(MessageConst.MSG, ErrorInfo.DB_DATA_REPEAT.getMsg());
            } else {
                String msg = ex.getCause() == null ? ex.toString() : ex.getCause().toString();
                model.put(MessageConst.CODE, ErrorInfo.UNKNOWN_ERROR.getCode());
                model.put(MessageConst.MSG, StringUtils.isEmpty(msg) ? ErrorInfo.UNKNOWN_ERROR.getMsg() : msg);
            }

            response.setStatus(HttpStatus.SC_BAD_REQUEST);
            MappingJackson2JsonView jsonView = new MappingJackson2JsonView();
            return new ModelAndView(jsonView, model);
        }
        return null;
    }

    @Override
    public int getOrder() {
        return HandlerOrder.get(getClass());
    }
}
