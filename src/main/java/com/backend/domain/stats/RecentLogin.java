package com.backend.domain.stats;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.OffsetDateTime;

@Data
@Builder
@AllArgsConstructor
public class RecentLogin {
    private final Long id;
    private final String username;
    private final OffsetDateTime lastLoginAt; // timestamptz 매핑
}