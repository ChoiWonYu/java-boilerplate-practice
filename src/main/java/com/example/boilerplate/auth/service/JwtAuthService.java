package com.example.boilerplate.auth.service;

import com.example.boilerplate.auth.controller.dto.SigninRequest;
import com.example.boilerplate.auth.controller.dto.SignupRequest;
import com.example.boilerplate.common.exception.CustomException;
import com.example.boilerplate.common.exception.ErrorCode;
import com.example.boilerplate.common.jwt.JwtProvider;
import com.example.boilerplate.member.entity.Member;
import com.example.boilerplate.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
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
        .orElseThrow(()->new CustomException(ErrorCode.MEMBER_NOT_FOUND));

    String accessToke=jwtProvider.createAccessToken(member.getEmail());

    return accessToke;
  }

  public Member findMemberByJwt(String token) {
    String memberFormId = jwtProvider.getPaylaod(token);

    return memberRepository.findByEmail(memberFormId)
        .orElseThrow(()->new CustomException(ErrorCode.MEMBER_NOT_FOUND));
  }
}
