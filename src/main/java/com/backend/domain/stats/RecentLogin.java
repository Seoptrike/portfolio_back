package com.backend.domain.stats;

import java.time.OffsetDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class RecentLogin {
  private final Long id;
  private final String username;
  private final OffsetDateTime lastLoginAt; // timestamptz 매핑
}
