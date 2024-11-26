package org.example.delivery;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
@SpringBootTest
public class JedisTest {
    @Autowired
    private RedisTemplate redisTemplate;
    @Test
    public void testString(){
      redisTemplate.opsForValue().set("city","beijing");
    }
}
