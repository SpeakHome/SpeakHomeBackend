package com.ExcludedHouse.speakHome.profiling.mapping;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration("profileMappingConfiguration")
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
