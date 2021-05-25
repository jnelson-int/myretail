package com.jnelsonint.myretail.repository;

import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

import com.jnelsonint.myretail.domain.ProductPricing;

@Repository
public interface ProductPricingRepository extends ReactiveCrudRepository<ProductPricing, Integer> {

}
