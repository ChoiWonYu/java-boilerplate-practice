package com.example.boilerplate.board.controller.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@AllArgsConstructor
public class BoardCreateRequest {

  @NotBlank(message="제목은 필수 입력값입니다.")
  private String title;

  @NotBlank(message="내용은 필수 입력값입니다.")
  private String content;
}
