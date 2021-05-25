package com.jnelsonint.myretail.service;

import org.springframework.stereotype.Service;

import com.jnelsonint.myretail.domain.ProductPricing;

import reactor.core.publisher.Mono;

@Service
public class ProductPricingService {

  public Mono<ProductPricing> getById(Integer id) {
    // TODO
    return Mono.empty();
  }
}
