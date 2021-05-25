package com.jnelsonint.myretail.ext.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.jnelsonint.myretail.ext.config.RedSkyFeignClientConfig;
import com.jnelsonint.myretail.ext.model.RedSkyProductInfoDTO;

@FeignClient(name = "red-sky-client", url = "${red-sky.base-url}", configuration = RedSkyFeignClientConfig.class)
public interface RedSkyFeignClient {

  @GetMapping(value = "/v3/pdp/tcin/{tcin}", consumes = "application/json")
  RedSkyProductInfoDTO getPdpByTcinV3(@PathVariable("tcin") String productId,
      @RequestParam("excludes") List<String> excludes);
}
