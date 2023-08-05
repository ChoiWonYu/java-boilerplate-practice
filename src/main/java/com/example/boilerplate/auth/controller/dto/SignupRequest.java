package com.example.boilerplate.auth.controller.dto;

import com.example.boilerplate.member.entity.Member;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class SignupRequest {

  @NotBlank(message = "이메일은 필수 입력값입니다.")
  private String email;

  @NotBlank(message = "비밀번호는 필수 입력값입니다.")
  private String password;

  @NotBlank(message = "이름은 필수 입력값입니다.")
  private String name;

  public Member toEntity() {
    return Member.builder()
        .email(email)
        .password(password)
        .name(name)
        .build();
  }
}
