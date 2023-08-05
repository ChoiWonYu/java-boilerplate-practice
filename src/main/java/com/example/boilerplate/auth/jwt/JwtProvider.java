package com.example.boilerplate.auth.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import java.util.Date;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Slf4j
//암호화, 복호화, 검증 로직
public class JwtProvider {
  private static final long TOKEN_EXPIRE_TIME = 1000 * 60 * 60; //1시간

  @Value("${jwt.secret}")
  private String secretKey;


  public String createAccessToken(String userFormId) {
    Claims claims = Jwts.claims()
        .setSubject(userFormId);

    Date now = new Date();
    Date accessTokenExpiresIn = new Date(now.getTime() + TOKEN_EXPIRE_TIME);

    return Jwts.builder()
        .setIssuedAt(now)
        .setClaims(claims)
        .setExpiration(accessTokenExpiresIn)
        .signWith(SignatureAlgorithm.HS512,secretKey)
        .compact();
  }

  public boolean isValidToken(String token) {
    try {
      parseClaims(token);
      return true;
    } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
      log.info("잘못된 JWT 서명입니다.");
    } catch (ExpiredJwtException e) {
      log.info("만료된 JWT 토큰입니다.");
    } catch (UnsupportedJwtException e) {
      log.info("지원되지 않는 JWT 토큰입니다.");
    } catch (IllegalArgumentException e) {
      log.info("JWT 토큰이 잘못되었습니다.");
    }
    log.error("error");
    return false;
  }

  public String getUserFormId(String token){
    Jws<Claims> claim = parseClaims(token);
    return claim.getBody()
        .getSubject();
  }

  public Jws<Claims> parseClaims(String token) {
    return Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token);
  }
}
