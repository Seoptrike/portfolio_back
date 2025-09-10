# 섭트폴리오 Backend

[![Render Deploy](https://img.shields.io/badge/render-deployed-blue?logo=render)](https://portfolio-back-prod.onrender.com)
[![CI/CD](https://github.com/Seoptrike/portfolio_back/actions/workflows/deploy.yml/badge.svg)](https://github.com/Seoptrike/portfolio_back/actions)

> Spring Boot 기반의 개인 포트폴리오 관리 시스템 백엔드 API

---

## 🛠 기술 스택

- **Spring Boot** 3.5.3 + **Java** 21
- **Spring Security** + **JWT** (쿠키 기반 인증)
- **MyBatis** 3.0.4 + **PostgreSQL**
- **Docker** + **Render** 배포
- **ImageKit** SDK, **Swagger** OpenAPI
- **PMD**, **Spotless** (코드 품질)

---

## ⚡ 빠른 시작

```bash
# 클론
git clone https://github.com/Seoptrike/portfolio_back.git
cd portfolio_back

# 환경변수 설정
# application.yml에서 DB, JWT 시크릿 등 설정

# 실행
./gradlew bootRun
# → http://localhost:8080
```

---

## 🎯 주요 기능

- **REST API**: 포트폴리오 데이터 CRUD
- **JWT 인증**: 쿠키 기반 로그인/권한 관리
- **이미지 업로드**: ImageKit 연동 API
- **헬스체크**: `/health` 엔드포인트
- **API 문서**: Swagger UI 자동 생성

---

## 📁 프로젝트 구조

```
src/main/java/com/backend/
├── controller/     # REST 컨트롤러
├── service/       # 비즈니스 로직
├── mapper/        # MyBatis 매퍼
├── domain/        # DTO, VO 클래스
├── config/        # 설정 (Security, DB 등)
└── security/      # JWT, 인증 처리
```

---

## 🚀 배포

**Docker + Render 자동배포** - GitHub Actions CI/CD

- **Backend API**: https://portfolio-back-prod.onrender.com
- **Swagger UI**: https://portfolio-back-prod.onrender.com/swagger-ui.html
- **Health Check**: https://portfolio-back-prod.onrender.com/health

---

## 📜 주요 API

```bash
# 인증
POST /api/auth/login      # 로그인
POST /api/auth/register   # 회원가입
GET  /api/auth/check      # 인증 상태 확인

# 포트폴리오
GET    /api/projects/{username}    # 프로젝트 목록
POST   /api/projects              # 프로젝트 생성
GET    /api/aboutme/{username}    # 소개 조회
POST   /api/imagekit/upload       # 이미지 업로드

# 시스템
GET /health                       # 헬스체크
```

---

## 🛠 개발 명령어

```bash
./gradlew bootRun        # 애플리케이션 실행
./gradlew test           # 테스트 실행
./gradlew pmdMain        # 정적 분석
./gradlew spotlessApply  # 코드 포맷팅
./gradlew build          # 빌드
```