package anpopo.stockissue.facade;

import anpopo.stockissue.service.OptimisticLockStockService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class OptimisticLockStockFacade {
    Logger log = LoggerFactory.getLogger(OptimisticLockStockFacade.class);
    private final OptimisticLockStockService optimisticLockStockService;

    public OptimisticLockStockFacade (OptimisticLockStockService optimisticLockStockService) {
        this.optimisticLockStockService = optimisticLockStockService;
    }

    public void decrease (Long id, Long quantity) throws InterruptedException {
        while (true) {
            try {
                optimisticLockStockService.decrease(id, quantity);
                break;
            } catch (Exception e) {
                log.error("[ERROR] version crashed!! message : {}", e.getMessage(), e);
                Thread.sleep(50L);
            }
        }
    }
}
