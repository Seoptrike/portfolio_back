package com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AboutMeDetailVO {
    private int detailId;
    private int aboutId;
    private String title;
    private String content;
    private int sort;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
