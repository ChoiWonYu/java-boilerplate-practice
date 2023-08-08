package com.example.boilerplate.board.controller.dto;

import com.example.boilerplate.board.entity.Board;
import com.example.boilerplate.member.entity.Member;
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

  public Board toEntity(Member member){
    return Board.builder()
        .title(this.title)
        .content(this.content)
        .member(member)
        .build();
  }
}
