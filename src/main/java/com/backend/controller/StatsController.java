package com.backend.controller;

import com.backend.domain.stats.DailyCount;
import com.backend.domain.stats.DauComparison;
import com.backend.domain.stats.RecentLogin;
import com.backend.service.stats.StatsService;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/admin/stats")
@RequiredArgsConstructor
public class StatsController {

  private final StatsService statsService;

  /** 대시보드 KPI 카드 (오늘 DAU, 최근30일 MAU, 오늘 가입자, 활성 회원수) */
  @GetMapping("/kpis")
  // @PreAuthorize("hasRole('ADMIN')")
  public Map<String, Object> getKpis(@RequestParam(defaultValue = "true") boolean excludeInternal) {
    Map<String, Object> res = new HashMap<>();
    res.put("todayDau", statsService.getTodayDAU(excludeInternal));
    res.put("mau30d", statsService.getMAULast30d(excludeInternal));
    res.put("todaySignups", statsService.getTodaySignups());
    res.put("activeUsers", statsService.getActiveUserCount());
    return res;
  }

  /** DAU 시계열 (기본: 최근 7일) */
  @GetMapping("/dau/range")
  // @PreAuthorize("hasRole('ADMIN')")
  public List<DailyCount> getDauRange(
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate startDate,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate endDate,
      @RequestParam(defaultValue = "true") boolean excludeInternal) {
    if (endDate == null) endDate = LocalDate.now(); // KST 기준 쿼리로 맞춤
    if (startDate == null) startDate = endDate.minusDays(6); // 최근 7일
    return statsService.getDauRange(startDate, endDate, excludeInternal);
  }

  /** 오늘/어제 DAU 비교 */
  @GetMapping("/dau/compare")
  // @PreAuthorize("hasRole('ADMIN')")
  public DauComparison getDauCompare(@RequestParam(defaultValue = "true") boolean excludeInternal) {
    return statsService.getTodayYesterdayDAU(excludeInternal);
  }

  /** 가입자 시계열 (기본: 최근 7일) */
  @GetMapping("/signups/range")
  // @PreAuthorize("hasRole('ADMIN')")
  public List<DailyCount> getSignupRange(
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate startDate,
      @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
          LocalDate endDate) {
    if (endDate == null) endDate = LocalDate.now();
    if (startDate == null) startDate = endDate.minusDays(6);
    return statsService.getSignupRange(startDate, endDate);
  }

  /** 최근 로그인 사용자 목록 (기본: 최근 24시간, 최대 20명) */
  @GetMapping("/logins/recent")
  // @PreAuthorize("hasRole('ADMIN')")
  public List<RecentLogin> getRecentLogins(
      @RequestParam(required = false) Integer sinceHours,
      @RequestParam(defaultValue = "20") int limit) {
    if (sinceHours == null) sinceHours = 24;
    if (limit <= 0) limit = 20;
    return statsService.getRecentLogins(sinceHours, limit);
  }

  /** 활성 일반회원 수 (role=1) */
  @GetMapping("/users/active-count")
  // @PreAuthorize("hasRole('ADMIN')")
  public Map<String, Long> getActiveUserCount() {
    return Map.of("activeUsers", statsService.getActiveUserCount());
  }
}
