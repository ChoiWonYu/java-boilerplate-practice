package com.example.boilerplate.board.entity;

import com.example.boilerplate.member.entity.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Board {

  @Id
  @GeneratedValue(strategy = GenerationType.UUID)
  private Long id;

  private String title;

  private String content;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name="member_id")
  private Member member;

}
