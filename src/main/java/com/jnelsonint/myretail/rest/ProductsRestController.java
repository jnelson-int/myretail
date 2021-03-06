package com.jnelsonint.myretail.rest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jnelsonint.myretail.provider.ProductProvider;
import com.jnelsonint.myretail.rest.model.ProductDTO;
import com.jnelsonint.myretail.rest.model.ProductUpdateDTO;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProductsRestController {

  private final ProductProvider provider;

  @GetMapping("/products/{id}")
  public Mono<ResponseEntity<ProductDTO>> getProductById(@PathVariable Integer id) {
    return provider.getById(id)
        .map(product -> ResponseEntity.ok(product))
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

  @PutMapping(value = "/products/{id}", consumes = "application/json")
  public Mono<ResponseEntity<ProductDTO>> updatePricing(@PathVariable Integer id, @RequestBody ProductUpdateDTO update) {
    return provider.updatePricing(id, update.getUpdatedPrice().getValue())
        .map(product -> ResponseEntity.ok(product))
        .switchIfEmpty(Mono.just(ResponseEntity.notFound().build()));
  }

}
