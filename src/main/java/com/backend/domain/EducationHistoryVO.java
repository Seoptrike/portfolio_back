package com.backend.domain;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class EducationHistoryVO {
    private int education_id;
    private int user_id;
    private String school_name;
    private String major;
    private LocalDate start_date;
    private LocalDate end_date;

}
