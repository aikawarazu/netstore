package com.fc.configs;

import static java.nio.charset.StandardCharsets.UTF_8;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * spring mvc 相关配置类
 *
 * @author hao19
 */
@Configuration
public class WebConfig {

  private WebMvcConfigurer webMvcConfigurer = new WebMvcConfigurer() {
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
      registry.addViewController("/index").setViewName("/hello"); // 这里就不转发到controller了，而是直接转发到template
//      registry.addViewController("/manage/category/add").setViewName("manage/addCategory");
//      registry.addViewController("/500").setViewName("/500");
//      registry.addViewController("/502").setViewName("/502");
//      registry.addViewController("/509").setViewName("/500");
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
      configurer.defaultContentType(MediaType.APPLICATION_JSON_UTF8,new MediaType(MediaType.TEXT_PLAIN, UTF_8));
    }
  };

  @Bean
  WebMvcConfigurer webMvcConfigurer() {
    return webMvcConfigurer;
  }
  @Bean
  WebDataBinder webDataBinder(){
    return new WebDataBinder(null);
  }
}