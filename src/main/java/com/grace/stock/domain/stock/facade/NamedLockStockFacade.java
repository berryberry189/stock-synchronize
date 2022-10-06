package com.grace.stock.domain.stock.facade;

import com.grace.stock.domain.stock.repository.LockRepository;
import com.grace.stock.domain.stock.service.NamedLockStockService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class NamedLockStockFacade {

  private final NamedLockStockService stockService;
  private final LockRepository lockRepository;

  @Transactional
  public void decrease(Long id, long quantity) throws InterruptedException {

    try {
      lockRepository.getLock(id.toString());
      stockService.decrease(id, quantity);
    } finally {
      lockRepository.releaseLock(id.toString());
    }


  }

}
