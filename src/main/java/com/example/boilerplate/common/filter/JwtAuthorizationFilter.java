package com.example.boilerplate.common.filter;

import com.example.boilerplate.auth.jwt.JwtProvider;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.util.PatternMatchUtils;

@Slf4j
@RequiredArgsConstructor
public class JwtAuthorizationFilter implements BearerTokenAuthorizationFilter{

  private String[] whiteListURI=new String[]{};

  private final JwtProvider jwtProvider;

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest httpServletRequest = (HttpServletRequest) request;
    HttpServletResponse httpServletResponse = (HttpServletResponse) response;

    if(checkWhiteList(httpServletRequest.getRequestURI())){
      chain.doFilter(request,response);
      return;
    }

    String authorization = httpServletRequest.getHeader("Authorization");

    if(!hasAuthorization(authorization)|!isBearerToken(authorization)){
      httpServletResponse.sendError(HttpStatus.UNAUTHORIZED.value(),"토큰이 없습니다.");
      return;
    }

    try{
      String token=getToken(httpServletRequest);
      jwtProvider.parseClaims(token);
      chain.doFilter(request,response);
    }catch (SecurityException | MalformedJwtException e) {
      log.info("잘못된 JWT 서명입니다.");
    } catch (ExpiredJwtException e) {
      log.info("만료된 JWT 토큰입니다.");
    } catch (UnsupportedJwtException e) {
      log.info("지원되지 않는 JWT 토큰입니다.");
    } catch (IllegalArgumentException e) {
      log.info("JWT 토큰이 잘못되었습니다.");
    }
  }

  @Override
  public boolean hasAuthorization(String authorizationHeader) {
    return authorizationHeader!=null;
  }

  @Override
  public boolean isBearerToken(String token) {
    return token.startsWith("Bearer ");
  }

  private boolean checkWhiteList(String uri) {
    return PatternMatchUtils.simpleMatch(whiteListURI, uri);
  }

  private String getToken(HttpServletRequest request){
    return request.getHeader("Authorization").substring(7);
  }
}
