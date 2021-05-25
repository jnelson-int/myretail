package com.jnelsonint.myretail.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductPricing {

  private Integer productId;
  private BigDecimal price;
}
