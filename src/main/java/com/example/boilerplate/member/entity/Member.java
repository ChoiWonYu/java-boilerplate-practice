package com.example.boilerplate.member.entity;

import com.example.boilerplate.board.entity.Board;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "member_id")
  private Long id;

  @Column(unique = true)
  private String email;

  private String password;

  private String name;

  @OneToMany(mappedBy = "member")
  private List<Board> boards=new ArrayList<>();

  @Builder
  public Member(String email, String password, String name) {
    this.email=email;
    this.password=password;
    this.name=name;
  }
}
