package com.backend.domain;


import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class UserStacks {
    private int user_stack_id;
    private int user_id;
    private int stack_id;
    private int score;

}
