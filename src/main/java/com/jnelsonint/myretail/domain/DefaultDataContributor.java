package com.jnelsonint.myretail.domain;

import java.math.BigDecimal;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import com.jnelsonint.myretail.service.ProductPricingService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class DefaultDataContributor implements ApplicationListener<ContextRefreshedEvent> {
  private final ProductPricingService repo;

  @Override
  public void onApplicationEvent(ContextRefreshedEvent event) {
    BigDecimal price = BigDecimal.valueOf(123.45);
    for (Integer productId = 13860427; productId < 13860429; productId++) {
      repo.save(new ProductPricing()
          .setProductId(productId)
          .setPrice(price))
          .block();
      price = price.add(BigDecimal.valueOf(1));
    }
  }

}
