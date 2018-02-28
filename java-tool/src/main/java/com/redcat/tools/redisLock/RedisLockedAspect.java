package com.redcat.tools.redisLock;

/**
 * Created by zhiyu.zhou on 2018/2/28.
 */
public class RedisLockedAspect {

    private RedisTemplate<String, Long> redisTemplate;

    // 切点：controller包中任意类下包含注解RedisLocked的方法被调用时

    private boolean lock(String redisKey, long timeout, long expire, long sleeptime) {

        long now = System.currentTimeMillis();
        try {
            while (System.currentTimeMillis() - now < timeout) {
                //redis: setNX
                Boolean locked = redisTemplate.opsForValue().setIfAbsent(redisKey, System.currentTimeMillis() + expire + 1);
                if(null != locked && locked) {
                    //get lock
                    return true;
                }
                //no lock then judge expired?
                //redis: GET
                long oldValue = redisTemplate.opsForValue().get(redisKey);
                //expired and old value still exist(no unlocked)
                if(oldValue < System.currentTimeMillis()
                        && oldValue == redisTemplate.opsForValue().getAndSet(redisKey, System.currentTimeMillis() + expire + 1)) {
                    //get lock
                    return true;
                }
                //wait and retry
                Thread.sleep(sleeptime);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
}
