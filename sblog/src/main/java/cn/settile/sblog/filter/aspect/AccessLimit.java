package cn.settile.sblog.filter.aspect;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

/**
 * @author : lzjyz
 * @date : 2020-02-20 18:35
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface AccessLimit {
    /**
     * 时间类型，默认毫秒
     * @return
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS ;

    /**
     * 多长时间内限制，默认 60
     * @return
     */
    long time () default 60;

    /**
     * 单位时间内能访问多少次，默认10次
     * @return
     */
    int count () default 10;
}
