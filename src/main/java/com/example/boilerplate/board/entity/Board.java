package com.example.boilerplate.board.entity;

import com.example.boilerplate.board.controller.dto.BoardCommonResponse;
import com.example.boilerplate.board.controller.dto.BoardUpdateRequest;
import com.example.boilerplate.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
@NoArgsConstructor
public class Board {

  @Id
  @GeneratedValue
  @UuidGenerator
  private UUID id;

  private String title;

  private String content;

  private String imageUrl;

  private int views;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="member_id")
  private Member writer;


  public void updateBoard(BoardUpdateRequest boardUpdateRequest){
    this.title=boardUpdateRequest.getTitle();
    this.content=boardUpdateRequest.getContent();
  }

  public BoardCommonResponse toDto(){
    return BoardCommonResponse.builder()
        .id(id)
        .writer(writer)
        .title(title)
        .content(content)
        .build();
  }

  @Builder
  public Board(String title,String content,String imageUrl,Member member){
    this.title=title;
    this.content=content;
    this.imageUrl=imageUrl;
    this.writer=member;
  }
}
