package com.jnelsonint.myretail.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.config.AbstractReactiveMongoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

import lombok.Getter;
import lombok.Setter;

@EnableReactiveMongoRepositories(basePackages = "com.jnelsonint.myretail.repository")
@Configuration
@ConfigurationProperties("myretail.data")
@Setter
@Getter
public class MongoReactiveConfiguration extends AbstractReactiveMongoConfiguration {

  private String databaseName;

}
