package com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UserStackRequestDTO {
    private int userId;
    private List<StackWithScore> stackList;
}
