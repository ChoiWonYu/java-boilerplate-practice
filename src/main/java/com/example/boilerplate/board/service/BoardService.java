package com.example.boilerplate.board.service;

import com.example.boilerplate.board.controller.dto.BoardCommonResponse;
import com.example.boilerplate.board.controller.dto.BoardCreateRequest;
import com.example.boilerplate.board.controller.dto.BoardUpdateRequest;
import com.example.boilerplate.board.entity.Board;
import com.example.boilerplate.board.repository.BoardRepository;
import com.example.boilerplate.common.exception.CustomException;
import com.example.boilerplate.common.exception.ErrorCode;
import com.example.boilerplate.member.entity.Member;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;

  public BoardCommonResponse createBoard(BoardCreateRequest boardCreateRequest, Member member) {
    Board board=boardRepository.save(boardCreateRequest.toEntity(member));
    return board.toDto();
  }

  public BoardCommonResponse updateBoard(UUID boardId,BoardUpdateRequest boardUpdateRequest, Member member) {
    Board board=boardRepository.findById(boardId)
        .orElseThrow(()->new CustomException(ErrorCode.BOARD_NOT_FOUND));

    validateMember(member,board);

    board.updateBoard(boardUpdateRequest);

    return board.toDto();
  }

  public BoardCommonResponse deleteBoard(UUID boardId, Member member) {
    Board board=boardRepository.findById(boardId)
        .orElseThrow(()->new CustomException(ErrorCode.BOARD_NOT_FOUND));

    validateMember(member,board);

    boardRepository.delete(board);

    return board.toDto();
  }

  private boolean isMineBoard(Member member,Board board) {
    return member.equals(board.getWriter());
  }

  private void validateMember(Member member,Board board){
    if(!isMineBoard(member,board))throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
  }

}
