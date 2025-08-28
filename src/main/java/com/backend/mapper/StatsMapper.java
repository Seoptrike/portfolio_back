package com.backend.mapper;

import org.apache.ibatis.annotations.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public interface StatsMapper {
    /** 오늘 DAU (회원 기준, 내부 트래픽 제외 옵션) */
    long selectTodayDAU(@Param("excludeInternal") Boolean excludeInternal);

    /** 최근 30일 MAU (내부 트래픽 제외 옵션) */
    long selectMAULast30d(@Param("excludeInternal") Boolean excludeInternal);

    /**
     * 기간별 DAU 시계열 (누락일 0 보정)
     * 반환: [{day: LocalDate(or String), count: Long}, ...]
     */
    List<Map<String, Object>> selectDAURange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate,
            @Param("excludeInternal") Boolean excludeInternal
    );

    /**
     * 오늘/어제 DAU 함께
     * 반환: {today_dau: Long, yesterday_dau: Long}
     */
    Map<String, Object> selectTodayYesterdayDAU(@Param("excludeInternal") Boolean excludeInternal);

    /** 오늘 가입자 수 */
    long selectTodaySignups();

    /**
     * 기간별 가입자 수 시계열
     * 반환: [{day: LocalDate(or String), count: Long}, ...]
     */
    List<Map<String, Object>> selectSignupRange(
            @Param("startDate") LocalDate startDate,
            @Param("endDate") LocalDate endDate
    );

    /**
     * 최근 로그인 사용자 목록
     * sinceHours: KST 기준 최근 N시간 (null이면 조건 없음)
     * limit: 최대 행 수
     * 반환: [{id, email, nickname, last_login_at}, ...]
     */
    List<Map<String, Object>> selectRecentLogins(
            @Param("sinceHours") Integer sinceHours,
            @Param("limit") Integer limit
    );

    /** 활성 일반회원 수 (role = 1) */
    long selectActiveUserCount();
}
