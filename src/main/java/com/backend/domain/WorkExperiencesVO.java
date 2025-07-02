package com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class WorkExperiencesVO {
    private int work_id;
    private int user_id;
    private String company_name;
    private String position;
    private LocalDate start_date;
    private LocalDate end_date;

}