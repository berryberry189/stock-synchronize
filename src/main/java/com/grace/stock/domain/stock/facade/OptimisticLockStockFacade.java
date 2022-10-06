package com.grace.stock.domain.stock.facade;

import com.grace.stock.domain.stock.service.OptimisticLockStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OptimisticLockStockFacade {

  private final OptimisticLockStockService optimisticLockStockService;

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
