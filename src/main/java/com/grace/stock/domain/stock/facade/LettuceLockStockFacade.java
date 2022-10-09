package com.grace.stock.domain.stock.facade;

import com.grace.stock.domain.stock.repository.RedisLockRepository;
import com.grace.stock.domain.stock.service.StockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class LettuceLockStockFacade {

  private final RedisLockRepository redisLockRepository;
  private final StockService stockService;

  public void decrease(Long key, long quantity) throws InterruptedException {

    // lock 획득시도
    while (!redisLockRepository.lock(key)) {
      // lock 획득 재시도 텀
      Thread.sleep(100);
    }

    try {
      stockService.decrease(key, quantity);
    } finally {
      redisLockRepository.unlock(key);
    }

  }


}
