package com.example.boilerplate.comment.service;

import com.example.boilerplate.comment.entity.Comment;
import com.example.boilerplate.member.entity.Member;
import java.util.UUID;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

  public Comment createComment(UUID boardId, Member member, String content) {

  }
}
