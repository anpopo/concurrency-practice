package anpopo.stockissue.repository;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.time.Duration;

@Repository
public class RedisLockRepository {

    private final RedisTemplate<String, String> redisTemplate;

    public RedisLockRepository (RedisTemplate<String, String> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public Boolean lock (Long key) {
        return redisTemplate
                .opsForValue()
                .setIfAbsent(generateKey(key), "lock", Duration.ofSeconds(2_000));
    }

    public Boolean unlock (Long key) {
        return redisTemplate
                .delete(generateKey(key));
    }

    private String generateKey (Long key) {
        return String.valueOf(key);
    }

}
