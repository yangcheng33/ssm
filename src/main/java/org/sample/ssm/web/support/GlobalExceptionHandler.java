package org.sample.ssm.web.support;

import org.apache.commons.lang3.StringUtils;
import org.sample.ssm.common.exception.AppExecutionException;
import org.sample.ssm.common.exception.BadRequestException;
import org.sample.ssm.common.exception.OperationForbiddenException;
import org.sample.ssm.common.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.stream.Collectors;

/**
 * 统一处理异常抛出。
 *
 * @author Yang Cheng
 * @version v 0.1 2017-11-07 16:36
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private void logErrorInfo(HttpServletRequest httpRequest, Exception ex) {
        logger.error("Error Request:\t{} {}", httpRequest.getMethod(), httpRequest.getServletPath());
        logger.error("Url Params: \t{}", getFormParams(httpRequest));
        if (StringUtils.containsIgnoreCase(httpRequest.getContentType(), "json")) {
            logger.error("Body Params: \t{}", getParams(httpRequest));
        }
        logger.error("", ex);
    }

    /**
     * 获取request body 中的json格式参数。
     * 如果取过一次request的inputstream，再取会抛出异常，建议使用requestWrapper。
     *
     */
    private String getParams(HttpServletRequest httpRequest) {
        StringBuilder builder = new StringBuilder();
        // todo 使用wraper
        try (BufferedReader reader = httpRequest.getReader()) {
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        } catch (Exception e) {
            logger.warn("Params have been read by @RequestBody");
        }
        return builder.toString();
    }

    /**
     * 获取表单方式提交的参数。
     */
    private String getFormParams(HttpServletRequest httpRequest) {
        return httpRequest.getParameterMap().entrySet().stream()
            .map(entry -> entry.getKey() + "=" + entry.getValue()[0]).collect(Collectors.joining("&"));
    }

    // 400
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public String handleBadRequestException(HttpServletRequest request, BadRequestException ex) {
        logErrorInfo(request, ex);
        return ex.getMessage();
    }

    // 403
    @ExceptionHandler(OperationForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    @ResponseBody
    public String handleOperationForbiddenException(HttpServletRequest request, OperationForbiddenException ex) {
        logErrorInfo(request, ex);
        return ex.getMessage();
    }

    // 404
    @ExceptionHandler(ResourceNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    @ResponseBody
    public String handleResourceNotFoundException(HttpServletRequest request, ResourceNotFoundException ex) {
        logErrorInfo(request, ex);
        return ex.getMessage();
    }

    // 500
    @ExceptionHandler({ AppExecutionException.class, RuntimeException.class, Exception.class })
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public String handleAppExecutionException(HttpServletRequest request, Exception ex) {
        logErrorInfo(request, ex);
        return ex.getMessage();
    }
}
