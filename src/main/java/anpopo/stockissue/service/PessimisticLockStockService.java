package anpopo.stockissue.service;

import anpopo.stockissue.domain.Stock;
import anpopo.stockissue.repository.StockRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PessimisticLockStockService {

    private final StockRepository stockRepository;

    public PessimisticLockStockService (StockRepository stockRepository) {
        this.stockRepository = stockRepository;
    }

    @Transactional
    public void decrease (Long id, Long quantity) {
        Stock stock = stockRepository.findWithPessimisticLockById(id);

        stock.decrease(quantity);
    }
}
