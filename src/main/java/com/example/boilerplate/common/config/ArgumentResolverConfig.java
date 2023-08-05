package com.example.boilerplate.common.config;

import com.example.boilerplate.auth.service.JwtAuthService;
import com.example.boilerplate.common.annotation.TokenInfoResolver;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@RequiredArgsConstructor
public class ArgumentResolverConfig implements WebMvcConfigurer {

  private final JwtAuthService jwtAuthService;

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    argumentResolvers.add(tokenInfoResolver());
  }

  @Bean
  public TokenInfoResolver tokenInfoResolver() {
    return new TokenInfoResolver(jwtAuthService);
  }

}
