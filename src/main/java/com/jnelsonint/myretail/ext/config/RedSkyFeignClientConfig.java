package com.jnelsonint.myretail.ext.config;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.cloud.openfeign.support.SpringDecoder;
import org.springframework.context.annotation.Bean;

import feign.RequestInterceptor;
import feign.codec.Decoder;

public class RedSkyFeignClientConfig {

  private static final String CLIENT_KEY_PARAM_NAME = "key";

  @Bean
  public RequestInterceptor authInterceptor(RedSkyProps props) {
    return template -> template.query(CLIENT_KEY_PARAM_NAME, props.getClientKey());
  }

  // dev note - this is a workaround needed due to known issue with this combination of versions
  // this workaround will cause performance issues, but is a viable fix for this case study
  @Bean
  public Decoder feignDecoder() {
    ObjectFactory<HttpMessageConverters> messageConverters = () -> {
      HttpMessageConverters converters = new HttpMessageConverters();
      return converters;
    };
    return new SpringDecoder(messageConverters);
  }

}
