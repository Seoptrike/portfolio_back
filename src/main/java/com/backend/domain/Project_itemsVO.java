package com.backend.domain;

import java.time.LocalDateTime;

public class Project_itemsVO {
    private int project_item_id;
    private int user_id;
    private String title;
    private String content;
    private String notion_url;
    private String github_url;
    private LocalDateTime created_at;
    private LocalDateTime updated_at;
}
