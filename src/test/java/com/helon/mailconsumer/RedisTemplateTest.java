package com.helon.mailconsumer;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: Helon
 * @Description: 集群redis，使用redisTemplate
 * @Data: Created in 2018/2/3 20:58
 * @Modified By:
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = MailConsumerApplication.class)
public class RedisTemplateTest {
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Test
    public void testRedis(){
       ValueOperations<String, String> opsForValue = redisTemplate.opsForValue();
       opsForValue.set("age", "1");
       System.out.println("age:" + opsForValue.get("age"));

    }
}
