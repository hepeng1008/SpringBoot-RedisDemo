package com.example.configuration;

import com.example.entity.RedisObjectSerializer;
import com.example.entity.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfig {

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory factory = new JedisConnectionFactory();

        return factory;
    }

    @Bean
    @ConditionalOnMissingBean(name="redisTemplate")
    public RedisTemplate<String,User> redisTemplate(JedisConnectionFactory jedisConnectionFactory){
        RedisTemplate<String,User> template = new RedisTemplate<String,User>();
        template.setConnectionFactory(jedisConnectionFactory);
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new RedisObjectSerializer());
        return template;
    }

    @Bean
    @ConditionalOnMissingBean(StringRedisTemplate.class)
    public StringRedisTemplate stringRedisTemplate(JedisConnectionFactory jedisConnectionFactory){
        StringRedisTemplate template = new StringRedisTemplate();
        template.setConnectionFactory(jedisConnectionFactory);
        return template;
    }
}
