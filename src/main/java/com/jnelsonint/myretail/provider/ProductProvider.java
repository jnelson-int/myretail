package com.jnelsonint.myretail.provider;

import java.math.BigDecimal;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.jnelsonint.myretail.domain.ProductPricing;
import com.jnelsonint.myretail.ext.feign.RedSkyFeignClient;
import com.jnelsonint.myretail.ext.model.RedSkyProductInfoDTO;
import com.jnelsonint.myretail.rest.model.ProductDTO;
import com.jnelsonint.myretail.rest.model.ProductPriceDTO;
import com.jnelsonint.myretail.service.ProductPricingService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ProductProvider {

  private final ProductPricingService productPricingService;
  private final RedSkyFeignClient     client;

  public Mono<ProductDTO> updatePricing(Integer id, BigDecimal price) {
    return get(id, productPricingService.getById(id)
        .defaultIfEmpty(new ProductPricing().setProductId(id))
        .map(pp -> pp.setPrice(price))
        .map(productPricingService::save)
        .flatMap(Function.identity()));
  }

  public Mono<ProductDTO> getById(Integer id) {
    return get(id, productPricingService.getById(id));
  }

  Mono<ProductDTO> get(Integer id, Mono<ProductPricing> pricing) {
    Mono<RedSkyProductInfoDTO> productNameInfo = getProductNameFromRedSky(id).subscribeOn(Schedulers.boundedElastic());
    return Mono.zip(productNameInfo.subscribeOn(Schedulers.boundedElastic()),
        pricing.subscribeOn(Schedulers.boundedElastic()), new ProductDTOFunc());
  }

  Mono<RedSkyProductInfoDTO> getProductNameFromRedSky(Integer id) {
    return Mono.fromCallable(() -> client.getPdpByTcinV3(String.valueOf(id), Stream.of(
        "taxonomy,price,promotion,bulk_ship,rating_and_review_reviews,rating_and_review_statistics,question_answer_statistics"
            .split(","))
        .collect(Collectors.toList())));
  }

  static class ProductDTOFunc implements BiFunction<RedSkyProductInfoDTO, ProductPricing, ProductDTO> {

    @Override
    public ProductDTO apply(RedSkyProductInfoDTO nameInfo, ProductPricing pricing) {
      return new ProductDTO()
          .setId(Integer.parseInt(nameInfo.getProduct().getItem().getTcin()))
          .setName(nameInfo.getProduct().getItem().getProductDescription().getTitle())
          .setCurrentPrice(new ProductPriceDTO().setValue(pricing.getPrice()));
    }

  }

}
