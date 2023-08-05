package com.example.boilerplate.common.annotation;

import com.example.boilerplate.auth.service.JwtAuthService;
import com.example.boilerplate.member.entity.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

@RequiredArgsConstructor
public class TokenInfoResolver implements HandlerMethodArgumentResolver {

  private final JwtAuthService jwtAuthService;

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    return parameter.hasParameterAnnotation(TokenInfo.class);
  }

  @Override
  public Member resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    String authorization = webRequest.getHeader("Authorization");
    String jwt = getJwtFromAuthorization(authorization);

    return jwtAuthService.findMemberByJwt(jwt);
  }

  private String getJwtFromAuthorization(String authorization) {
    return authorization.substring(7);
  }
}
