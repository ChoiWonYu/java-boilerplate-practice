package com.example.boilerplate.board.controller;

import com.example.boilerplate.board.entity.Board;
import com.example.boilerplate.board.repository.BoardRepository;
import com.example.boilerplate.board.service.BoardService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {
  private final BoardService boardService;
  private final BoardRepository boardRepository;

  @GetMapping("")
  public List<Board> getBoards(){
   return this.boardRepository.findAll();
  }

}
