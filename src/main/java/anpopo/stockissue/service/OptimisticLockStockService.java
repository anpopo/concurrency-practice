package anpopo.stockissue.service;

import anpopo.stockissue.domain.Stock;
import anpopo.stockissue.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class OptimisticLockStockService {

    private final StockRepository stockRepository;

    public OptimisticLockStockService (StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease (Long id, Long quantity) {
        Stock stock = stockRepository.findWithOptimisticLockById(id);
        stock.decrease(quantity);
    }
}
