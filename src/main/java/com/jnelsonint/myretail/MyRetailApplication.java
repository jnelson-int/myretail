package com.jnelsonint.myretail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.fasterxml.jackson.databind.ObjectMapper;

@EnableFeignClients(basePackages = "com.jnelsonint.myretail.ext.feign")
@SpringBootApplication
public class MyRetailApplication {

  public static void main(String[] args) {
    SpringApplication.run(MyRetailApplication.class, args);
  }

  @Bean
  public ObjectMapper defaultObjectMapper() {
    return new ObjectMapper();
  }

}
