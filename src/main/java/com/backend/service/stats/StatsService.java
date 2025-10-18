package com.backend.service.stats;

import com.backend.domain.stats.DailyCount;
import com.backend.domain.stats.DauComparison;
import com.backend.domain.stats.RecentLogin;
import java.time.LocalDate;
import java.util.List;

public interface StatsService {
  /** 오늘 DAU (회원 기준) */
  long getTodayDAU(boolean excludeInternal);

  /** 최근 30일 MAU */
  long getMAULast30d(boolean excludeInternal);

  /** 기간별 DAU 시계열 */
  List<DailyCount> getDauRange(LocalDate startDate, LocalDate endDate, boolean excludeInternal);

  /** 오늘/어제 DAU */
  DauComparison getTodayYesterdayDAU(boolean excludeInternal);

  /** 오늘 가입자 수 */
  long getTodaySignups();

  /** 기간별 가입자 수 시계열 */
  List<DailyCount> getSignupRange(LocalDate startDate, LocalDate endDate);

  /** 최근 로그인 사용자 목록 */
  List<RecentLogin> getRecentLogins(Integer sinceHours, int limit);

  /** 활성 일반회원 수 (role=1) */
  long getActiveUserCount();
}
