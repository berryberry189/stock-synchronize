package com.grace.stock.domain.stock.repository;

import com.grace.stock.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

// 편의를 위해,, 실무에서는 사용 x
public interface LockRepository extends JpaRepository<Stock, Long> {

  @Query(value = "select get_lock(:key, 3000)", nativeQuery = true)
  void getLock(String key);

  @Query(value = "select release_lock(:key)", nativeQuery = true)
  void releaseLock(String key);


}
