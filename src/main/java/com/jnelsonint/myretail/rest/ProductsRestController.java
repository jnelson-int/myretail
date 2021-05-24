package com.jnelsonint.myretail.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jnelsonint.myretail.rest.model.ProductDTO;

import reactor.core.publisher.Mono;

@RestController
public class ProductsRestController {

  @GetMapping("/products/{id}")
  public Mono<ProductDTO> getProductById(@PathVariable Integer id) {
    return Mono.empty();
  }
  
  
}
