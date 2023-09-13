package com.ExcludedHouse.speakHome.security.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("securityMappingConfiguration")
public class MappingConfiguration {
  @Bean
  public RoleMapper roleMapper() {
    return new RoleMapper();
  }

  @Bean
  public ProfileMapper profileMapper() {
    return new ProfileMapper();
  }
}
