package com.backend.domain.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
@AllArgsConstructor
public class DailyCount {
    private final LocalDate day;
    private final long count;
}
