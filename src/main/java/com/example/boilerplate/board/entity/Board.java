package com.example.boilerplate.board.entity;

import com.example.boilerplate.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.util.UUID;
import lombok.Getter;
import org.hibernate.annotations.UuidGenerator;

@Entity
@Getter
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

}
