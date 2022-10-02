package com.grace.stock.domain.stock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.grace.stock.domain.stock.Stock;
import com.grace.stock.domain.stock.repository.StockRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class StockServiceTest {

  @Autowired
  private StockService stockService;
  @Autowired
  private StockRepository stockRepository;

  // 데이터 세팅
  @BeforeEach
  void before() {
    Stock stock = new Stock(1L, 100);
    stockRepository.save(stock);
  }

  // 테스트 완료 후 데이터 삭제
  @AfterEach
  void after() {
    stockRepository.deleteAll();
  }

  @Test
  void stock_decrease() {
    Long stockId = 1L;

    stockService.decrease(stockId, 1);

    Stock stock = stockRepository.findById(stockId).orElseThrow();

    assertEquals(99, stock.getQuantity());
  }
}