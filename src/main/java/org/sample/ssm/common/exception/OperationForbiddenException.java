package org.sample.ssm.common.exception;

/**
 * 处理无操作权限情况。
 *
 * @author Yang Cheng
 * @version v 0.1 2017-11-07 16:36
 */
public class OperationForbiddenException extends SsmException {

    public OperationForbiddenException(String errorInfo) {
        super(errorInfo);
    }

    public OperationForbiddenException() {
        super("无权限执行此操作");
    }
}
