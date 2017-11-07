package org.sample.ssm.common.exception;

/**
 * 其他异常类。
 *
 * @author Yang Cheng
 * @version v 0.1 2017-11-07 16:36
 */
public class AppExecutionException extends SsmException {

    public AppExecutionException(String errorInfo) {
        super(errorInfo);
    }

    public AppExecutionException() {
        super("数据处理出错。");
    }
}
