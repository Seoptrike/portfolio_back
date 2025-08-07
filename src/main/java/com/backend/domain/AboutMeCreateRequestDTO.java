package com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AboutMeCreateRequestDTO {
    private String username;
    private String title;
    private String content;
}
