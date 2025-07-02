package com.backend.domain;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class About_meVO {
    private int about_id;
    private int user_id;
    private String title;
    private String content;

}
