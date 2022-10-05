package com.grace.stock.domain.stock.facade;

import com.grace.stock.domain.stock.service.OptimisticLockStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OptimisticLockFacade {

  private final OptimisticLockStockService optimisticLockStockService;

  @Transactional
  public void decrease(Long id, long quantity) throws InterruptedException {

    while (true) {
      try {
        optimisticLockStockService.decrease(id, quantity);
        break;
      } catch (Exception e) {
          Thread.sleep(50);
      }
    }

  }

}
