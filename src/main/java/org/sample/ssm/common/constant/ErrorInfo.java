package org.sample.ssm.common.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * 错误码 <br>
 * <strong>
 * 除去OK和UNKNOWN_ERROR,剩下所有错误码的范围从1001~9999<br>
 * 10开头的错误作为通用错误使用<br>
 * 使用4位错误码定义错误信息,前2位为模块名,剩下的2位为错误码
 * </strong>
 */
public enum ErrorInfo {

    OK(0, ""),

    /**
     * 通用错误
     */
    UNKNOWN_ERROR(1000, "服务器错误,请联系管理员!"),
    ARGUMENG_ERROR(1001, "参数错误!"),
    DB_FAILURE(1002, "数据库操作出错!"),
    OPER_FAILED_ERROR(1003, "操作失败!"),
    DB_INSERT_FAILURE(1004, "数据插入失败!"),
    DB_UPDATE_FAILURE(1005, "没有数据被更新!"),
    DB_DETELE_FAILURE(1006, "没有数据被删除!"),
    DB_DATA_REPEAT(1007, "重复的数据!"),
    DB_DATA_NOT_FOUND(1008, "找不到记录!"),

    LOGIN_ERROR(2101, "登录错误!"),
    SSO_CHECK_ERROR(2102, "SSO校验错误!"),
    NOT_LOGIN_ERROR(2103, "尚未登录,请刷新页面!"),

    PERMISSION_FORBIDDEN(3101, "权限不足!"),
    /*
     * 业务错误信息
     */
    BZ_ERROR(1111, "ERROR!");

    private static Map<Integer, ErrorInfo> errorInfoMap = new HashMap<Integer, ErrorInfo>();

    static {
        ErrorInfo[] values = ErrorInfo.values();
        for (ErrorInfo errorInfo : values) {
            errorInfoMap.put(errorInfo.getCode(), errorInfo);
        }
    }

    private final int    code;
    private final String msg;

    ErrorInfo(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static ErrorInfo getByCode(int code) {
        return errorInfoMap.get(code);
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public String printInfo() {
        StringBuilder sb = new StringBuilder();
        sb.append("code: ");
        sb.append(this.getCode());
        sb.append("\tmessage: ");
        sb.append(this.getMsg());
        return sb.toString();
    }

}
