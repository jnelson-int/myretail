package com.jnelsonint.myretail.domain;

import java.math.BigDecimal;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
@Document
public class ProductPricing {

  @Id
  private Integer    productId;
  private BigDecimal price;
}
