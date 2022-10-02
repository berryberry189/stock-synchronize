package com.grace.stock.domain.stock.service;

import com.grace.stock.domain.stock.Stock;
import com.grace.stock.domain.stock.repository.StockRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockService {

  private final StockRepository stockRepository;

  public void decrease(Long id, long quantity) {
    // get stock
    Stock stock = stockRepository.findById(id).orElseThrow();

    // 재고감소
    stock.decrease(quantity);

    // 저장
    stockRepository.save(stock);
  }

}
