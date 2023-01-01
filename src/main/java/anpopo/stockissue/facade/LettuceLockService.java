package anpopo.stockissue.facade;

import anpopo.stockissue.repository.RedisLockRepository;
import anpopo.stockissue.service.StockService;
import org.springframework.stereotype.Service;

@Service
public class LettuceLockService {
    private final RedisLockRepository redisLockRepository;
    private final StockService stockService;

    public LettuceLockService (RedisLockRepository redisLockRepository, StockService stockService) {
        this.redisLockRepository = redisLockRepository;
        this.stockService = stockService;
    }

    public void decrease (Long id, Long quantity) throws InterruptedException {
        while (!redisLockRepository.lock(id)) {
            Thread.sleep(100L);
        }

        try {
            stockService.decrease(id, quantity);
        } finally {
            redisLockRepository.unlock(id);
        }
    }
}
