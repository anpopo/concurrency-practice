package anpopo.stockissue.repository;

import anpopo.stockissue.domain.NamedLockSession;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface NamedLockRepository extends JpaRepository<NamedLockSession, Long> {

    @Query(value = "select get_lock(:key, 10)", nativeQuery = true)
    void getLock (String key);

    @Query(value = "select release_lock(:key)", nativeQuery = true)
    void releaseLock (String key);
}
