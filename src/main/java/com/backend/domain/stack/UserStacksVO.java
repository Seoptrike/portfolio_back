package com.backend.domain.stack;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserStacksVO {
  private int user_stack_id;
  private int user_id;
  private int stack_id;
  private int score;
}
