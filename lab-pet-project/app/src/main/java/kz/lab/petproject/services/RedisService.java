package kz.lab.petproject.services;

import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.HashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class RedisService {
    private final RedisTemplate<String, Object> redisDb1Template;

    public Object readData(String key) {
        return redisDb1Template.opsForValue().get(key);
    }

    public <T> T readHashField(String key, Object hashKey) {
        return (T) redisDb1Template.opsForHash().get(key, hashKey);
    }

    public void writeHashField(String key, Object hashKey, Object value) {
        redisDb1Template.opsForHash().put(key, hashKey, value);
    }
}
