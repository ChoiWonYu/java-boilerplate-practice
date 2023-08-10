package com.example.boilerplate.comment.controller;

import com.example.boilerplate.comment.controller.dto.CommentCommonResponseDto;
import com.example.boilerplate.comment.controller.dto.CommentCreateRequestDto;
import com.example.boilerplate.comment.entity.Comment;
import com.example.boilerplate.comment.service.CommentService;
import com.example.boilerplate.common.annotation.TokenInfo;
import com.example.boilerplate.member.entity.Member;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/boards/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  //dto로 변환하는 부분은 컨트롤러에서 처리하도록 나중에 리팩토링
  @PostMapping("/{boardId}")
  public ResponseEntity<CommentCommonResponseDto> createComment(@PathVariable
  UUID boardId, @RequestBody CommentCreateRequestDto commentCreateRequestDto,
      @TokenInfo Member member) {
    Comment createdComment = commentService.createComment(boardId, member,
        commentCreateRequestDto.getContent());
    CommentCommonResponseDto response = createdComment.toDto();
    return new ResponseEntity<>(response, HttpStatus.CREATED);
  }

  @PutMapping
  public void editComment() {

  }

  @DeleteMapping
  public void removeComment() {

  }

}
