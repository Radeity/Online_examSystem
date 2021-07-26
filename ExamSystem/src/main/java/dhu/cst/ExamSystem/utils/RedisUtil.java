package dhu.cst.ExamSystem.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisUtil {
    @Autowired
    RedisTemplate redisTemplate;

    //判断是否存在key
    public boolean hasKey(String key){
        return redisTemplate.hasKey(key);
    }

    //从redis中获取值
    public Object get(String key){
        System.out.println("redis get");
        return redisTemplate.opsForValue().get(key);
    }

    //删除redis缓存
    public void del(String key){
        redisTemplate.delete(key);
        System.out.println("redis del");
    }

    //向redis插入值
    public boolean set(final String key,Object value){
        boolean result = false;
        try{
            redisTemplate.opsForValue().set(key,value);
            result = true;
            System.out.println("redis set");
        }catch (Exception e){
            e.printStackTrace();
        }
        return  result;
    }

}
