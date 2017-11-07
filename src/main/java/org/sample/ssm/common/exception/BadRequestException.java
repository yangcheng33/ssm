package org.sample.ssm.common.exception;

/**
 * 处理非法参数、请求数据格式有误等情况。
 *
 * @author Yang Cheng
 * @version v 0.1 2017-11-07 16:36
 */
public class BadRequestException extends SsmException {

    public BadRequestException(String message) {
        super(message);
    }

    public BadRequestException() {
        super("请求信息有误。");
    }
}
