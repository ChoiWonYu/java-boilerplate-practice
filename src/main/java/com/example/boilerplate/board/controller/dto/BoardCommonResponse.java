package com.example.boilerplate.board.controller.dto;

import com.example.boilerplate.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BoardCommonResponse {

  private UUID id;

  private String title;

  private String content;

  private String writer;

  @Builder
  public BoardCommonResponse(UUID id,String title, String content, Member writer){
    this.id=id;
    this.title=title;
    this.writer=writer.getName();
    this.content=content;
  }
}
