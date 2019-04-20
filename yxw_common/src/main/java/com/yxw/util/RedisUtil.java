package com.yxw.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author:阿倪
 * @Date: 2019/4/18 17:27
 * @Description:
 * @return:
 * @throws:
 */
@Component
public class RedisUtil {


    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    public void setString(String key, Object data) {
        setString(key, data, null);
    }

    public void setString(String key, Object data, Long timeout) {
        if (data instanceof String) {
            String value = (String) data;
            stringRedisTemplate.opsForValue().set(key, value);
        } else {
            String value = data.toString();
            stringRedisTemplate.opsForValue().set(key, value);
        }
        if (timeout != null) {
            stringRedisTemplate.expire(key, timeout, TimeUnit.SECONDS);
        }
    }

    public String getString(String key) {
        return (String) stringRedisTemplate.opsForValue().get(key);
    }

    public void delKey(String key) {
        stringRedisTemplate.delete(key);
    }

}
