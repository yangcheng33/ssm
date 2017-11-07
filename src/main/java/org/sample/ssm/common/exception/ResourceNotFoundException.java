package org.sample.ssm.common.exception;

/**
 * 处理用户请求访问的资源不存在情况。
 *
 * @author Yang Cheng
 * @version v 0.1 2017-11-07 16:36
 */
public class ResourceNotFoundException extends SsmException {

    public ResourceNotFoundException() {
        super("找不到相应资源");
    }

    public ResourceNotFoundException(String message) {
        super(message);
    }
}
