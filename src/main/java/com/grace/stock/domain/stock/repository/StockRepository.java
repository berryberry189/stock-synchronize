package com.grace.stock.domain.stock.repository;

import com.grace.stock.domain.stock.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<Stock, Long> {

}
