package com.backend.domain.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class DauComparison {
  private final long today;
  private final long yesterday;

  public long diff() {
    return today - yesterday;
  }

  public double growthRate() {
    return (yesterday == 0) ? 0.0 : (today - yesterday) / (double) yesterday;
  }
}
