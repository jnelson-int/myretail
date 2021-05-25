package com.jnelsonint.myretail.provider;

import java.util.function.BiFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.stereotype.Service;

import com.jnelsonint.myretail.domain.ProductPricing;
import com.jnelsonint.myretail.ext.feign.RedSkyFeignClient;
import com.jnelsonint.myretail.ext.model.RedSkyProductInfoDTO;
import com.jnelsonint.myretail.rest.model.ProductDTO;
import com.jnelsonint.myretail.rest.model.ProductDTO.Price;
import com.jnelsonint.myretail.service.ProductPricingService;

import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;
import reactor.core.scheduler.Schedulers;

@Service
@RequiredArgsConstructor
public class ProductProvider {

  private final ProductPricingService productPricingService;
  private final RedSkyFeignClient     client;

  public Mono<ProductDTO> getById(Integer id) {

    Mono<RedSkyProductInfoDTO> productNameInfo = getProductNameFromRedSky(id).subscribeOn(Schedulers.boundedElastic());
    Mono<ProductPricing> pricing = productPricingService.getById(id).subscribeOn(Schedulers.boundedElastic());

    return Mono.zip(productNameInfo, pricing, new ProductDTOFunc());
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
          .setCurrentPrice(new Price().setValue(pricing.getPrice())
          // TODO set currency
          );
    }

  }

}
