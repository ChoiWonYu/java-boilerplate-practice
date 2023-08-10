package com.example.boilerplate.board.entity;

import com.example.boilerplate.board.controller.dto.BoardCommonResponse;
import com.example.boilerplate.board.controller.dto.BoardUpdateRequest;
import com.example.boilerplate.comment.entity.Comment;
import com.example.boilerplate.member.entity.Member;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.UuidGenerator;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Getter
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Board {

  @Id
  @GeneratedValue
  @UuidGenerator
  @Column(name = "board_id")
  private UUID id;

  private String title;

  private String content;

  private String imageUrl;

  private int views;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="member_id")
  private Member writer;

  @OneToMany(mappedBy = "board")
  private List<Comment> comments=new ArrayList<>();

  @CreatedDate
  @Column(updatable = false)
  private LocalDateTime createdAt;

  @LastModifiedDate
  private LocalDateTime updatedAt;


  public void updateBoard(BoardUpdateRequest boardUpdateRequest){
    this.title=boardUpdateRequest.getTitle();
    this.content=boardUpdateRequest.getContent();

    if(boardUpdateRequest.getImageUrl()!=null){
      this.imageUrl=boardUpdateRequest.getImageUrl();
    }
    else{
      this.imageUrl=null;
    }
  }

  public BoardCommonResponse toDto(){
    return BoardCommonResponse.builder()
        .id(id)
        .writer(writer)
        .title(title)
        .content(content)
        .createdAt(createdAt)
        .imageUrl(imageUrl)
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
