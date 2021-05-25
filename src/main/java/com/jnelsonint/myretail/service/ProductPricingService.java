package com.jnelsonint.myretail.service;

import org.springframework.stereotype.Service;

import com.jnelsonint.myretail.domain.ProductPricing;
import com.jnelsonint.myretail.repository.ProductPricingRepository;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class ProductPricingService {

  private final ProductPricingRepository repo;

  public Mono<ProductPricing> getById(Integer id) {
    return repo.findById(id);
  }

  public Mono<ProductPricing> save(ProductPricing entity) {
    return repo.save(entity);
  }
}
