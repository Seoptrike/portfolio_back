package com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ProjectResponseDTO {
    private int projectId;
    private String title;
    private String description;
    private LocalDate start_date;
    private LocalDate end_date;
    private String github_url;
    private String deploy_url;
    private String notion_url;
    private String thumbnail_url;
    private List<TechStacksVO> stack_names;
}
