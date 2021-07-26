package dhu.cst.ExamSystem.config;

import dhu.cst.ExamSystem.entity.User;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;

//Redis对象序列化为Json
@Configuration
@EnableAutoConfiguration
public class MyRedisConfig {
    @Bean
    public RedisTemplate<Object, User> redisTemplate(RedisConnectionFactory redisConnectionFactory){
        RedisTemplate<Object, User> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);

        Jackson2JsonRedisSerializer<User> serial = new Jackson2JsonRedisSerializer<>(User.class);
        template.setDefaultSerializer(serial);
        return template;
    }
}


