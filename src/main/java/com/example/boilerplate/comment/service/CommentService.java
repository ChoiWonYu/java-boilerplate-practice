package com.example.boilerplate.comment.service;

import com.example.boilerplate.board.entity.Board;
import com.example.boilerplate.board.repository.BoardRepository;
import com.example.boilerplate.board.service.BoardService;
import com.example.boilerplate.comment.entity.Comment;
import com.example.boilerplate.comment.repository.CommentRepository;
import com.example.boilerplate.member.entity.Member;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class CommentService {

  private final BoardService boardService;
  private final CommentRepository commentRepository;

  public Comment createComment(UUID boardId, Member member, String content) {
    Board board=boardService.getBoardDetail(boardId);
    Comment comment = new Comment(content,member,board);

    comment.addToBoard(board);

    commentRepository.save(comment);
    return comment;
  }
}
