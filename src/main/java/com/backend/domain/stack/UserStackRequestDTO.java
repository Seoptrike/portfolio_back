package com.backend.domain.stack;

import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserStackRequestDTO {
  private int userId;
  private List<StackWithScore> stackList;
}
