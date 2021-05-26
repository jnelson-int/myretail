package com.jnelsonint.myretail.domain;

import java.math.BigDecimal;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Stream;

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
    AtomicInteger priceIncrementor = new AtomicInteger(1);
    Stream.of(13860427, 54456119, 13264003, 12954218)
        .forEach(productId -> repo.save(new ProductPricing()
            .setProductId(productId)
            .setPrice(price.add(BigDecimal.valueOf(priceIncrementor.getAndIncrement()))))
            .block());
  }

}
