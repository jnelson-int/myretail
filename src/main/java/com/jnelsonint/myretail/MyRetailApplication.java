package com.jnelsonint.myretail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

import com.fasterxml.jackson.databind.ObjectMapper;

@EnableWebFlux
@EnableReactiveMongoRepositories(basePackages = "com.jnelsonint.myretail.repository")
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
