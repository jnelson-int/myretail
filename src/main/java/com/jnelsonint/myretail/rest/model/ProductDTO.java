package com.jnelsonint.myretail.rest.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter
@Setter
@Accessors(chain = true)
public class ProductDTO {

  private Integer         id;
  private String          name;
  @JsonProperty("current_price")
  private ProductPriceDTO currentPrice;

}
