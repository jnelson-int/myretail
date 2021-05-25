package com.jnelsonint.myretail.rest.model;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ProductDTO {

  private Integer id;
  private String  name;
  @JsonProperty("current_price")
  private Price   currentPrice;

  @Getter
  @Setter
  @Accessors(chain = true)
  public static class Price {
    private BigDecimal value;
    @JsonProperty("currency_code")
    private String     currency;
  }
}
