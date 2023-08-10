package com.example.boilerplate.comment.service;

import com.example.boilerplate.board.entity.Board;
import com.example.boilerplate.board.repository.BoardRepository;
import com.example.boilerplate.board.service.BoardService;
import com.example.boilerplate.comment.entity.Comment;
import com.example.boilerplate.member.entity.Member;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CommentService {

  private final BoardService boardService;

  public Comment createComment(UUID boardId, Member member, String content) {
    Board board=boardService.getBoardDetail(boardId);
    return Comment.createComment(content,member,board);
  }
}
