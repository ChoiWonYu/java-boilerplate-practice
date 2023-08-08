package com.example.boilerplate.common.response;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class PageResponse<T> {

  private List<T> results;

  private CommonPageInfo pageInfo;

}
