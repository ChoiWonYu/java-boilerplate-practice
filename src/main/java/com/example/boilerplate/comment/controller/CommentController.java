package com.example.boilerplate.comment.controller;

import com.example.boilerplate.comment.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController(value = "/comments")
@RequiredArgsConstructor
public class CommentController {

  private final CommentService commentService;

  @PostMapping
  public void createComment(){

  }

  @PutMapping
  public void editComment(){

  }

  @DeleteMapping
  public void removeComment(){

  }

}
