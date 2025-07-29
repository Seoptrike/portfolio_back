package com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProjectRequestDTO {
    private String username;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private String githubUrl;
    private String deployUrl;
    private String notionUrl;
    private String thumbnailUrl;
    private List<Integer> stackIds; // 기술스택 ID 목록
}
