package com.example.boilerplate.board.controller.dto;

import com.example.boilerplate.comment.controller.dto.CommentOwnerShipResponseDto;
import com.example.boilerplate.member.entity.Member;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@JsonInclude(Include.NON_NULL)
public class BoardDetailResonseDto {

  private UUID id;

  private String title;

  private String content;

  private String writer;

  private String imageUrl;

  private LocalDateTime createdAt;

  private List<CommentOwnerShipResponseDto> comments;

  private boolean isMine;

  @Builder
  public BoardDetailResonseDto(UUID id,String title, String content, Member writer,LocalDateTime createdAt,String imageUrl,List<CommentOwnerShipResponseDto> comments,boolean isMine){
    this.id=id;
    this.title=title;
    this.writer=writer.getName();
    this.content=content;
    this.imageUrl=imageUrl;
    this.createdAt=createdAt;
    this.comments=comments;
    this.isMine=isMine;
  }
}
