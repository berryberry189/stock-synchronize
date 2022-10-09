package com.grace.stock.domain.stock;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Stock {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long productId;

  private long quantity;

  @Version
  private Long version;

  public Stock(Long productId, long quantity) {
    this.productId = productId;
    this.quantity = quantity;
  }

  public void decrease(long quantity) {
    if(this.quantity - quantity < 0) {
      throw new IllegalArgumentException("수량은 0 보다 작을 수 없습니다.");
    }
    this.quantity -= quantity;
  }
}
