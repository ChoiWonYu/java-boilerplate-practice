package com.example.boilerplate.board.controller.dto;

import com.example.boilerplate.board.entity.Board;
import com.example.boilerplate.common.response.CommonPageInfo;
import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

@NoArgsConstructor
@Setter
@Getter
public class BoardPaginationDto {
  @Min(1)
  private int page=1;

  @Min(1)
  private int size=3;

  private String keyword;

  public boolean hasKeyword() {
    return keyword!=null;
  }

  public CommonPageInfo toPageInfo(Page<Board> page){
    return new CommonPageInfo(this.page,size,page.getTotalElements(),page.getTotalPages());
  }
}
