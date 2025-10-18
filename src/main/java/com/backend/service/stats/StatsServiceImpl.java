package com.backend.service.stats;

import com.backend.domain.stats.DailyCount;
import com.backend.domain.stats.DauComparison;
import com.backend.domain.stats.RecentLogin;
import com.backend.mapper.StatsMapper;
import java.time.*;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StatsServiceImpl implements StatsService {

  private final StatsMapper mapper;

  @Override
  public long getTodayDAU(boolean excludeInternal) {
    return mapper.selectTodayDAU(excludeInternal);
  }

  @Override
  public long getMAULast30d(boolean excludeInternal) {
    return mapper.selectMAULast30d(excludeInternal);
  }

  @Override
  public List<DailyCount> getDauRange(
      LocalDate startDate, LocalDate endDate, boolean excludeInternal) {
    List<Map<String, Object>> rows = mapper.selectDAURange(startDate, endDate, excludeInternal);
    return rows.stream()
        .map(r -> new DailyCount(toLocalDate(r.get("day")), toLong(r.get("count"))))
        .toList();
  }

  @Override
  public DauComparison getTodayYesterdayDAU(boolean excludeInternal) {
    Map<String, Object> row = mapper.selectTodayYesterdayDAU(excludeInternal);
    long today = toLong(row.get("today_dau"));
    long yesterday = toLong(row.get("yesterday_dau"));
    return new DauComparison(today, yesterday);
  }

  @Override
  public long getTodaySignups() {
    return mapper.selectTodaySignups();
  }

  @Override
  public List<DailyCount> getSignupRange(LocalDate startDate, LocalDate endDate) {
    List<Map<String, Object>> rows = mapper.selectSignupRange(startDate, endDate);
    return rows.stream()
        .map(r -> new DailyCount(toLocalDate(r.get("day")), toLong(r.get("count"))))
        .toList();
  }

  @Override
  public List<RecentLogin> getRecentLogins(Integer sinceHours, int limit) {
    return mapper.selectRecentLogins(sinceHours, limit).stream()
        .map(
            r ->
                RecentLogin.builder()
                    .id(toLong(r.get("id")))
                    .username((String) r.get("username"))
                    .lastLoginAt(toOffsetDateTime(r.get("last_login")))
                    .build())
        .toList();
  }

  @Override
  public long getActiveUserCount() {
    return mapper.selectActiveUserCount();
  }

  /* ===== helpers ===== */
  private static long toLong(Object v) {
    if (v == null) return 0L;
    if (v instanceof Number n) return n.longValue();
    return Long.parseLong(String.valueOf(v));
  }

  private static LocalDate toLocalDate(Object v) {
    if (v == null) return null;
    if (v instanceof LocalDate d) return d;
    return LocalDate.parse(String.valueOf(v)); // "2025-08-28"
  }

  private static OffsetDateTime toOffsetDateTime(Object v) {
    if (v == null) return null;
    if (v instanceof OffsetDateTime odt) return odt;
    if (v instanceof java.sql.Timestamp ts) return ts.toInstant().atOffset(ZoneOffset.UTC);
    if (v instanceof java.util.Date dt) return dt.toInstant().atOffset(ZoneOffset.UTC);
    // 문자열로 넘어오는 경우
    return OffsetDateTime.parse(String.valueOf(v));
  }
}
