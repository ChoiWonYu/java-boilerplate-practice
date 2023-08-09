package com.example.boilerplate.board.controller;

import com.example.boilerplate.board.controller.dto.BoardCommonResponse;
import com.example.boilerplate.board.controller.dto.BoardCreateRequest;
import com.example.boilerplate.board.controller.dto.BoardPaginationDto;
import com.example.boilerplate.board.controller.dto.BoardUpdateRequest;
import com.example.boilerplate.board.controller.dto.ImageUploadResponseDto;
import com.example.boilerplate.board.service.BoardService;
import com.example.boilerplate.common.annotation.TokenInfo;
import com.example.boilerplate.common.response.PageResponse;
import com.example.boilerplate.member.entity.Member;
import jakarta.validation.Valid;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/boards")
@RequiredArgsConstructor
public class BoardController {

  private final BoardService boardService;

  //좀 더 복잡한 페이지네이션 구현해보기
  @GetMapping
  public ResponseEntity<PageResponse<BoardCommonResponse>> getBoards(BoardPaginationDto boardPaginationDto) {
    PageResponse<BoardCommonResponse> boardsPageResponse = this.boardService.getBoards(boardPaginationDto);
    return new ResponseEntity<>(boardsPageResponse,HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<BoardCommonResponse> createBoard(
      @Valid @RequestBody BoardCreateRequest boardCreateRequest, @TokenInfo Member member) {
    BoardCommonResponse response = this.boardService.createBoard(boardCreateRequest,member);
    return new ResponseEntity<>(response,HttpStatus.CREATED);
  }

  @PostMapping("/image")
  public ResponseEntity<ImageUploadResponseDto> uploadImage(@RequestParam(value = "image")MultipartFile image){
    String imageUrl=boardService.uploadImage(image);
    ImageUploadResponseDto response=new ImageUploadResponseDto(imageUrl);
    return new ResponseEntity<>(response,HttpStatus.CREATED);
  }

  @PutMapping("/{boardId}")
  public ResponseEntity<BoardCommonResponse> updateBoard(
      @Valid @RequestBody BoardUpdateRequest boardUpdateRequest, @PathVariable UUID boardId,
      @TokenInfo Member member) {
    BoardCommonResponse updatedBoard = this.boardService.updateBoard(boardId, boardUpdateRequest, member);
    return new ResponseEntity<>(updatedBoard,HttpStatus.OK);
  }

  @DeleteMapping("/{boardId}")
  public ResponseEntity<BoardCommonResponse> deleteBoard(@PathVariable UUID boardId, @TokenInfo Member member) {
    BoardCommonResponse board = this.boardService.deleteBoard(boardId, member);
    return new ResponseEntity<>(board,HttpStatus.OK);
  }
}
