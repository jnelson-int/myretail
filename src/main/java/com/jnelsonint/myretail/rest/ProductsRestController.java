package com.jnelsonint.myretail.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jnelsonint.myretail.provider.ProductProvider;
import com.jnelsonint.myretail.rest.model.ProductDTO;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProductsRestController {

  private final ProductProvider provider;

  @GetMapping("/products/{id}")
  public Mono<ProductDTO> getProductById(@PathVariable Integer id) {
    return provider.getById(id);
  }

}
