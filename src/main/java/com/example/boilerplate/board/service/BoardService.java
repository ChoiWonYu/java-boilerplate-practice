package com.example.boilerplate.board.service;

import com.example.boilerplate.aws.s3.S3Service;
import com.example.boilerplate.board.controller.dto.BoardCommonResponse;
import com.example.boilerplate.board.controller.dto.BoardCreateRequest;
import com.example.boilerplate.board.controller.dto.BoardPaginationDto;
import com.example.boilerplate.board.controller.dto.BoardUpdateRequest;
import com.example.boilerplate.board.entity.Board;
import com.example.boilerplate.board.repository.BoardRepository;
import com.example.boilerplate.common.exception.CustomException;
import com.example.boilerplate.common.exception.ErrorCode;
import com.example.boilerplate.common.response.PageResponse;
import com.example.boilerplate.member.entity.Member;

import java.util.List;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class BoardService {

  private final BoardRepository boardRepository;
  private final S3Service s3Service;

  @Transactional(readOnly = true)
  public PageResponse<BoardCommonResponse> getBoards(BoardPaginationDto boardPaginationDto) {

    Page<Board> paginationBoards=getPaginationBoards(boardPaginationDto);

    List<BoardCommonResponse> results=paginationBoards.stream().map(Board::toDto).toList();

    return new PageResponse<>(results,boardPaginationDto.toPageInfo(paginationBoards));
  }

  public Board createBoard(BoardCreateRequest boardCreateRequest, Member member) {
    Board board = boardRepository.save(boardCreateRequest.toEntity(member));
    return board;
  }

  public Board updateBoard(UUID boardId, BoardUpdateRequest boardUpdateRequest,
      Member member) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));

    validateMember(member, board);

    board.updateBoard(boardUpdateRequest);

    return board;
  }

  public String uploadImage(MultipartFile image) {
    String imageUrl=s3Service.uploadFile(image);
    return imageUrl;
  }


  public Board deleteBoard(UUID boardId, Member member) {
    Board board = boardRepository.findById(boardId)
        .orElseThrow(() -> new CustomException(ErrorCode.BOARD_NOT_FOUND));

    validateMember(member, board);

    boardRepository.delete(board);

    return board;
  }

  private boolean isMineBoard(Member member, Board board) {
    return member.equals(board.getWriter());
  }

  private void validateMember(Member member, Board board) {
    if (!isMineBoard(member, board)) {
      throw new CustomException(ErrorCode.UNAUTHORIZED_USER);
    }
  }

  private Page<Board> getPaginationBoards(BoardPaginationDto boardPaginationDto) {
    PageRequest pageRequest=makePageRequest(boardPaginationDto);

    if (boardPaginationDto.hasKeyword()) {
      return this.boardRepository.findByTitleContaining(
          boardPaginationDto.getKeyword(), pageRequest);
    }
    return this.boardRepository.findAll(pageRequest);
  }

  private PageRequest makePageRequest(BoardPaginationDto boardPaginationDto) {
    return PageRequest.of(boardPaginationDto.getPage() - 1,
        boardPaginationDto.getSize());
  }

  public Board getBoardDetail(UUID boardId) {
    Board board=boardRepository.findById(boardId)
        .orElseThrow(()->new CustomException(ErrorCode.BOARD_NOT_FOUND));
    return board;
  }
}
