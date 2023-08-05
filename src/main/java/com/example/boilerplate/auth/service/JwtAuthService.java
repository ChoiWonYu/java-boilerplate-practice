package com.example.boilerplate.auth.service;

import com.example.boilerplate.auth.controller.dto.SigninRequest;
import com.example.boilerplate.auth.controller.dto.SignupRequest;
import com.example.boilerplate.common.jwt.JwtProvider;
import com.example.boilerplate.member.entity.Member;
import com.example.boilerplate.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class JwtAuthService implements AuthService{

  private final MemberRepository memberRepository;

  private final JwtProvider jwtProvider;

  @Override
  public String register(SignupRequest signupRequest) {
    Member member=signupRequest.toEntity();
    memberRepository.save(member);

    return "회원가입에 성공했습니다.";
  }

  @Override
  public String login(SigninRequest signinRequest) {
    Member member=memberRepository.findByEmail(signinRequest.getEmail())
        .orElse(null);

    String accessToke=jwtProvider.createAccessToken(member.getEmail());

    return accessToke;
  }
}
