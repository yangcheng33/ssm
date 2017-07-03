package org.sample.ssm.common.exception;

import org.sample.ssm.common.constant.ErrorInfo;

/**
 * 自定义异常
 *
 * @author Yang Cheng
 * @date 2017-01-20
 */

public class WebRuntimeException extends RuntimeException {

    private int code;

    public WebRuntimeException(String message) {
        super(message);
    }

    public WebRuntimeException(ErrorInfo error) {
        super(error.getMsg());
        code = error.getCode();
    }

    public WebRuntimeException() {
    }

    public int getCode() {
        return code;
    }

    @Override
    public String toString() {
        String message = getLocalizedMessage();
        String info = "";
        if (code != 0) {
            info += Integer.toString(code);
        }
        return (message != null) ? (info + ": " + message) : info;
    }
}