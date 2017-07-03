package org.sample.ssm.web.interceptor;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 统计耗费时间的标记注解 <br>
 */
@Inherited
@Target({ ElementType.TYPE, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CountTime {

    /**
     * 最大响应时间
     */
    int maxMilles() default 5000;

    /**
     * 是否始终输出日志
     */
    boolean forcePrint() default false;
}
