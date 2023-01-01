package anpopo.stockissue.repository;

import anpopo.stockissue.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import javax.persistence.LockModeType;

public interface StockRepository extends JpaRepository<Stock, Long> {

    @Lock(value = LockModeType.PESSIMISTIC_WRITE)
    Stock findWithPessimisticLockById (Long id);

    @Lock(value = LockModeType.OPTIMISTIC)
    Stock findWithOptimisticLockById (Long id);
}
