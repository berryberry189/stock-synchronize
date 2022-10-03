package com.grace.stock.domain.stock.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.grace.stock.domain.stock.Stock;
import com.grace.stock.domain.stock.repository.StockRepository;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
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

  @Test
  void 동시에_100개의_요청() throws InterruptedException {
    int threadCount = 100;
    // 멀티 스레드 생성
    ExecutorService executorService = Executors.newFixedThreadPool(32);

    // 다른 스레드에서 수행중인 작업이 완료될때 까지 대기할 수 있도록 도와주는 class
    CountDownLatch latch = new CountDownLatch(threadCount);

    for(int i=0; i <threadCount; i++) {
      executorService.submit(() -> {
        try {
          stockService.decrease(1L, 1);
        } finally {
          latch.countDown();
        }
      });
    }

    latch.await();

    Stock stock = stockRepository.findById(1L).orElseThrow();

    // 테스트 실패됨
    assertEquals(0, stock.getQuantity());
  }


}