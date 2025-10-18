package com.backend.domain.stats;

import java.time.LocalDate;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DailyCount {
  private final LocalDate day;
  private final long count;
}
