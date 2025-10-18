package com.backend.config;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {

  @Bean
  public ModelMapper modelMapper() {
    ModelMapper modelMapper = new ModelMapper();

    /**
     * 매핑 전략 설정 MatchingStrategies.STRICT : 필드명이 정확히 일치할 때만 매핑합니다. (가장 안전)
     * MatchingStrategies.STANDARD : 기본적인 매핑 전략입니다. (기본값) MatchingStrategies.LOOSE : 이름이 유사하면 매핑합니다.
     */
    modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT); // STRICT 전략 사용

    return modelMapper;
  }
}
