package com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Setter
@Getter
public class AboutMeVO {
    private int aboutId;
    private int userId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
