package com.example.boilerplate.comment.entity;

import com.example.boilerplate.board.entity.Board;
import com.example.boilerplate.comment.controller.dto.CommentCommonResponseDto;
import com.example.boilerplate.comment.controller.dto.CommentOwnerShipResponseDto;
import com.example.boilerplate.member.entity.Member;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "board_id",nullable = false)
  private Board board;

  @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
  @JoinColumn(name = "member_id",nullable = false)
  private Member commentWriter;

  public Comment(String content,Member member,Board board){
    this.content=content;
    this.commentWriter=member;
    this.board=board;
  }

  public CommentCommonResponseDto toDto() {
    return new CommentCommonResponseDto(board.getId(),content,commentWriter.getName());
  }

  public CommentOwnerShipResponseDto toOwnerShipDto(Member member) {
    return new CommentOwnerShipResponseDto(board.getId(),content,commentWriter.getName(),commentWriter.equals(member));
  }

  public void addToBoard(Board board){
    board.getComments().add(this);
  }
}
