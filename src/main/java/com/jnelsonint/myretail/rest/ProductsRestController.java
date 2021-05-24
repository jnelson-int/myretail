package com.jnelsonint.myretail.rest;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jnelsonint.myretail.ext.feign.RedSkyFeignClient;
import com.jnelsonint.myretail.ext.model.RedSkyProductInfoDTO;
import com.jnelsonint.myretail.rest.model.ProductDTO;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class ProductsRestController {

  private final RedSkyFeignClient client;
  
  @GetMapping("/products/{id}")
  public Mono<ProductDTO> getProductById(@PathVariable Integer id) {
    
    return Mono.fromCallable(() -> {
      List<String> excludes = Stream.of("taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics".split(",")).collect(Collectors.toList());
      RedSkyProductInfoDTO productInfo = client.getByTcinV3(String.valueOf(id), excludes);
      return new ProductDTO()
          .setId(id)
          .setName(productInfo.getProduct().getItem().getProductDescription().getTitle());
    });
  }
  
  
}
