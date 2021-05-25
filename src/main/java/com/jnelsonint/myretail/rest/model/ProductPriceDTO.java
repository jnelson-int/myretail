package com.jnelsonint.myretail.rest.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ProductPriceDTO {
  private BigDecimal value;
  @JsonProperty("currency_code")
  private String currency = "USD";
  
  /*
   * dev note- setting currency code to default to USD here
   * <p>
   * in the real world, we might consider part of this service doing currency conversions based on desired currency requested by client
   */
}
