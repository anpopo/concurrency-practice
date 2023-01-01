package anpopo.stockissue.facade;

import anpopo.stockissue.repository.NamedLockRepository;
import anpopo.stockissue.service.StockService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class NamedLockStockFacade {

    private final NamedLockRepository namedLockRepository;
    private final StockService stockService;

    public NamedLockStockFacade (NamedLockRepository namedLockRepository, StockService stockService) {
        this.namedLockRepository = namedLockRepository;
        this.stockService = stockService;
    }

    @Transactional
    public void decrease (Long id, Long quantity) {

        try {
            namedLockRepository.getLock(id.toString());
            stockService.decreaseWithNamedLock(id, quantity);
        } finally {
            namedLockRepository.releaseLock(id.toString());
        }

    }
}
