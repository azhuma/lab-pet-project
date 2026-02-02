package kz.lab.petproject.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class RedisConfiguration {
    @Value("${redis-db1.host}")
    private String db1Host;

    @Value("${redis-db1.port}")
    private int db1Port;

    @Value("${redis-db1.database}")
    private int db1Database;

    @Bean(name = "redisDb1ConnectionFactory")
    public RedisConnectionFactory redisDb1ConnectionFactory() {
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration(db1Host, db1Port);
        config.setDatabase(db1Database);
        return new LettuceConnectionFactory(config);
    }

    @Bean(name = "redisDb1Template")
    public RedisTemplate<String, Object> redisDb1Template(@Qualifier("redisDb1ConnectionFactory") RedisConnectionFactory connectionFactory) {
        RedisTemplate<String, Object> template = new RedisTemplate<>();
        template.setConnectionFactory(connectionFactory);

        // выставляем сериалайзеры на key-value
        template.setKeySerializer(new StringRedisSerializer());
        template.setValueSerializer(new StringRedisSerializer());

        // выставляем сериалайзеры на хэши
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new StringRedisSerializer());

        return template;
    }
}
