package com.jnelsonint.myretail.ext.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
@ConfigurationProperties("red-sky")
public class RedSkyProps {

  private String clientKey;

}
