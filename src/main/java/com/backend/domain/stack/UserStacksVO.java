package com.backend.domain.stack;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserStacksVO {
  private int userStackId;
  private int userId;
  private int stackId;
  private int score;
}
