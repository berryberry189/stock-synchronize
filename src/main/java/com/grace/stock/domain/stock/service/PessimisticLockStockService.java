package com.grace.stock.domain.stock.service;

import com.grace.stock.domain.stock.Stock;
import com.grace.stock.domain.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PessimisticLockStockService {

  // 비관적 락(pessimistic lock)
  // 비관적 락은 Reeatable Read 또는 Serializable 정도의 격리성 수준에서 가능하다
  // 비관적 락이란 트랜잭션이 시작될 때 Shared Lock 또는 Exclusive Lock을 걸고 시작하는 방법이다
  // 즉, Shared Lock을 걸게 되면 write를 하기위해서는 Exclucive Lock을 얻어야하는데
  // Shared Lock이 다른 트랜잭션에 의해서 걸려 있으면 해당 Lock을 얻지 못해서 업데이트를 할 수 없다
  // 수정을 하기 위해서는 해당 트랜잭션을 제외한 모든 트랜잭션이 종료(commit) 되어야한다

  private final StockRepository stockRepository;

  @Transactional
  public void decrease(Long id, long quantity) {
    // get stock
    Stock stock = stockRepository.findByIdWithPessimisticLock(id);

    // 재고감소
    stock.decrease(quantity);

    // 저장
    stockRepository.saveAndFlush(stock);
  }

}
