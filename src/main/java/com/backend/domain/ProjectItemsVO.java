package com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class ProjectItemsVO {
    private int project_item_id;
    private int user_id;
    private String title;
    private String content;
    private String notion_url;
    private String github_url;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;

}
