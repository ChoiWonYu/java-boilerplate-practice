package com.example.boilerplate.comment.controller.dto;

import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentOwnerShipResponseDto {
  private UUID boardId;

  private String content;

  private String commentWriter;

  private boolean isMine;
}
