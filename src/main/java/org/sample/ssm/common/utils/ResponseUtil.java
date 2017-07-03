package org.sample.ssm.common.utils;

import com.google.common.collect.ImmutableMap;
import org.sample.ssm.common.constant.MessageConst;
import org.sample.ssm.common.constant.SessionConst;
import org.sample.ssm.common.constant.ErrorInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Collections;
import java.util.Map;

/**
 * response工具 <br>
 */
public final class ResponseUtil {

    static Logger logger = LoggerFactory.getLogger(ResponseUtil.class);

    private ResponseUtil() {
    }

    /**
     * 设置返回类型为json <br>
     * <br>
     *
     * @param response
     */
    public static void setResponseType2Json(HttpServletResponse response) {
        response.setContentType(MessageConst.JSON_FORMAT);
    }

    /**
     * 返回正常的json <br>
     * <br>
     *
     * @param data
     */
    public static Map returnOK(Map<String, ? extends Object> data) {
        return returnOK((Object) data);
    }

    /**
     * 返回正常的数据json <br>
     * 2015年2月12日:上午11:22:33<br>
     * <br>
     */
    public static Map returnOK(Object data) {
        if (data == null) {
            data = Collections.EMPTY_MAP;
        }
        Map<String, Object> json = ImmutableMap.of(MessageConst.CODE, MessageConst.STATUS_OK, "data", data);
        return json;
    }

    /**
     * 返回无效数据信息 <br>
     * <br>
     */
    public static String returnInvalid(Object data) {
        Map<String, Object> json = ImmutableMap.of(MessageConst.CODE, MessageConst.STATUS_INVALID, "msg", data);
        return JacksonUtil.toJson(json);
    }

    /**
     * 返回正常的数据json <br>
     * <br>
     *
     * @param data
     * @param totalCount
     */
    public static String returnOK(Object totalCount, Object data) {
        Map<String, Object> json = ImmutableMap
            .of(MessageConst.CODE, MessageConst.STATUS_OK, "totalcount", totalCount, "data", data);
        return JacksonUtil.toJson(json);
    }

    /**
     * 返回正常的json <br>
     * <br>
     */
    public static Map returnOK() {
        Map<String, ? extends Object> json = ImmutableMap.of(MessageConst.CODE, MessageConst.STATUS_OK);
        return json;
    }

    /**
     * errorinfo <br>
     */
    public static String returnError(String code, String msg) {

        return JacksonUtil.toJson(ImmutableMap.of(MessageConst.CODE, code, MessageConst.MSG, msg));
    }

    /**
     * errorinfo <br>
     *
     * @param errorInfo
     */
    public static Map returnError(ErrorInfo errorInfo) {
        int code = errorInfo.getCode();
        String msg = errorInfo.getMsg() == null ? "" : errorInfo.getMsg();
        if (errorInfo != ErrorInfo.NOT_LOGIN_ERROR) {
            logger.warn("response返回错误信息:{}", msg);
        }
        return ImmutableMap.of(MessageConst.CODE, code, MessageConst.MSG, msg);
    }

    /**
     * 返回参数错误 <br>
     * <br>
     *
     * @param e
     */
    public static String returnError(Exception e) {

        String code = String.valueOf(ErrorInfo.ARGUMENG_ERROR.getCode());
        String msg = e.getMessage() == null ? "" : e.getMessage();

        Map<String, String> json = ImmutableMap.of(MessageConst.CODE, code, MessageConst.MSG, msg);
        return JacksonUtil.toJson(json);
    }

    /**
     * 返回错误,提示未知错误<br>
     * <br>
     */
    public static Map returnError() {
        return returnError(ErrorInfo.UNKNOWN_ERROR);
    }

    /**
     * 向model中添加错误信息,用于向jsp反馈错误信息 <br>
     * <br>
     *
     * @param modelAndView
     * @param errorInfo
     */
    public static final ModelAndView addErrorInfo(ModelAndView modelAndView, ErrorInfo errorInfo) {
        modelAndView.addObject(MessageConst.CODE, errorInfo.getCode());
        modelAndView.addObject(MessageConst.MSG, errorInfo.getMsg());
        return modelAndView;
    }
}
