package com.example.boilerplate.common.config;

import com.example.boilerplate.auth.jwt.JwtProvider;
import com.example.boilerplate.common.filter.JwtAuthorizationFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.Filter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FilterConfig {

  @Bean
  public FilterRegistrationBean jwtFilter(JwtProvider jwtProvider) {
    FilterRegistrationBean<Filter> filterRegistrationBean = new FilterRegistrationBean<>();
    filterRegistrationBean.setFilter(new JwtAuthorizationFilter(jwtProvider));
    filterRegistrationBean.setOrder(1);
    return filterRegistrationBean;
  }
}
