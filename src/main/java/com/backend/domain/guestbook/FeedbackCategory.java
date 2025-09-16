package com.backend.domain.guestbook;

public enum FeedbackCategory {
    TECH_STACK(1, "기술 스택"),
    PROJECT_COMPLETION(2, "프로젝트 완성도"),
    CODE_QUALITY(3, "코드 품질"),
    UI_UX(4, "UI/UX"),
    PORTFOLIO_STRUCTURE(5, "포트폴리오 구성"),
    CAREER_EXPERIENCE(6, "경력/경험"),
    IMPROVEMENT(7, "개선사항"),
    OVERALL_EVALUATION(8, "전반적 평가");

    private final int code;
    private final String description;

    FeedbackCategory(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    // 코드로 enum 찾기
    public static FeedbackCategory fromCode(int code) {
        for (FeedbackCategory category : values()) {
            if (category.code == code) {
                return category;
            }
        }
        return null;
    }

    // 설명으로 enum 찾기 (기존 데이터 호환성을 위해)
    public static FeedbackCategory fromDescription(String description) {
        for (FeedbackCategory category : values()) {
            if (category.description.equals(description)) {
                return category;
            }
        }
        return null;
    }
}