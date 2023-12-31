package com.example.boilerplate.comment.controller.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CommentCommonResponseDto {

  private UUID boardId;

  private String content;

  private String commentWriter;
}
