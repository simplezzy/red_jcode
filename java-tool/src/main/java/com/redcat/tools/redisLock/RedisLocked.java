package com.redcat.tools.redisLock;

/**
 * Created by zhiyu.zhou on 2018/2/28.
 */

import java.lang.annotation.*;

/**
 * redisLock redis分布式锁 AOP
 * call: @RedisLocked(key = "'lock:bcr:submit:'+#scheduleId", value = "当前方案有其他人正在进行提交操作，请稍后再试", isWait = false)
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RedisLocked {
    //lock failed message
    String value() default "其他用户正在操作,请稍候重试";

    //锁键名 支持SPEL 可直接使用方法参数名 如{@code #argName}
    String key() default "redis_lock";

    //锁超时时长ms
    long expire() default 30000;

    //等待时长 ms
    long timeout() default 1000;

    //重试间隔时长
    long sleeptime() default 100;

    //获取失败，是继续等还是放弃
    boolean isWait() default true;
}
