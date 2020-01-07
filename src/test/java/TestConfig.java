import cn.nyse.config.SpringMybatisConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import redis.clients.jedis.Jedis;

import java.util.ArrayList;

/**
 * @Author: jiangyu
 * @Company: 东方标准
 * @Date: 2020/01/02/16:53
 * @Description:
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = SpringMybatisConfig.class)
public class TestConfig {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Autowired
    RedisTemplate<String,Object> redisTemplate;

    @Test
    public void testRedisConnectionFactory(){
        Jedis jedis = (Jedis) redisConnectionFactory.getConnection().getNativeConnection();
        System.out.println(jedis.keys("*"));
    }

    @Test
    public void testRedisTemplate(){
        ValueOperations<String, Object> valueOperations = redisTemplate.opsForValue();
        valueOperations.set("redisTemplate","test");
        valueOperations.set("沈铁锋","虎牙不求人");
        System.out.println(redisTemplate.keys("*"));
        System.out.println(redisTemplate.hasKey("redisTemplate"));

    }


    @Test
    public void testRedisTemplate2(){
        ArrayList<String> list = new ArrayList<>();
        list.add("lettuce");
        list.add("redis");
        redisTemplate.opsForList().leftPushAll("redisList",list);
    }
}
