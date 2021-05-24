package com.jnelsonint.myretail.ext.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class RedSkyProductInfoDTO {

  private Product product;

  @Getter
  @Setter
  public static class Product {
    private Item item;
  }

  @Getter
  @Setter
  public static class Item {
    private String                 tcin;
    @JsonProperty("product_description")
    private ItemProductDescription productDescription;
  }

  @Getter
  @Setter
  public static class ItemProductDescription {
    private String title;
  }
}
