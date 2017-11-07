package org.sample.ssm.common.exception;

/**
 * 基础异常类。
 *
 * @author Yang Cheng
 * @version v 0.1 2017-11-07 16:36
 */
public abstract class SsmException extends RuntimeException {

    private String errorInfo;

    public String getErrorInfo() {
        return errorInfo;
    }

    public SsmException(String errorInfo) {
        super(errorInfo);
        this.errorInfo = errorInfo;
    }

    @Override
    public String getMessage() {
        return errorInfo;
    }
}
