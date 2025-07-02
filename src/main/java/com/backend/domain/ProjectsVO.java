package com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProjectsVO {
    private int project_id;
    private int user_id;
    private String title;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
