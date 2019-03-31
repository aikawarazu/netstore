package com.fc.configs;

import com.fc.common.progress.CustomMultipartResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UploadResolverConfig {

  @Bean
  CustomMultipartResolver customMultipartResolver() {
    return new CustomMultipartResolver();
  }

}
