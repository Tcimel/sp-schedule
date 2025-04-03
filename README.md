
# 📆 Schedule Management API

Spring Boot 기반의 일정 관리 프로젝트입니다.
유저는 회원가입을 통해 등록되며, 유저별로 일정을 생성, 조회, 수정, 삭제할 수 있습니다.

---

## 📁 프로젝트 구조

```
src
├── entity
│   ├── BaseEntity.java         # 생성일/수정일 자동 처리용
│   ├── User.java               # 유저 도메인 엔티티
│   └── Schedule.java           # 일정 도메인 엔티티
│
├── controller
│   ├── UserController.java     # 유저 관련 요청 처리
│   └── ScheduleController.java # 일정 관련 요청 처리
│
├── service
│   ├── UserService.java        # 유저 관련 비즈니스 로직
│   └── ScheduleService.java    # 일정 관련 비즈니스 로직
│
├── repository
│   ├── UserRepository.java     # JPA 유저 저장소
│   └── ScheduleRepository.java # JPA 일정 저장소
│
├── dto
│   ├── SignUpRequestDto.java
│   ├── SignUpResponseDto.java
│   ├── UpdatePasswordDto.java
│   ├── UpdateScheduleRequestDto.java
│   ├── ScheduleRequestDto.java
│   ├── ScheduleResponseDto.java
│   └── UserResponseDto.java
│
└── ScheduleApplication.java    # 메인 클래스
```

---

## ✅ 주요 기능

### ✅ 일정 기능
- [x] 일정 등록 (`POST /schedules`)
- [x] 전체 일정 조회 (`GET /schedules`)
- [x] 일정 단건 조회 (`GET /schedules/{id}`)
- [x] 일정 수정 (`PATCH /schedules/{id}`)
- [x] 일정 삭제 (`DELETE /schedules/{id}`)

### ✅ 유저 기능
- [x] 회원가입 (`POST /users`)
- [x] 유저 목록 조회 (`GET /users`)
- [x] 유저 수정 (`PATCH /users/{id}`)
- [x] 유저 삭제 (`DELETE /users/{id}`)

---

## 🔧 개선 포인트

- [ ] ✅ `Service`와 `Controller` 단에서 ExceptionHandler 통일 처리 (e.g. `@ControllerAdvice`)
- [ ] ✅ DTO 정리: 필요 없는 필드 제거 & 명확한 역할 구분
- [ ] ✅ `ScheduleResponseDto(schedule)` 과 같이 생성자에 Entity 넘기는 대신 `.toDto(schedule)` 구조로 분리 추천
- [ ] ✅ 단위 테스트 코드 추가 (JUnit + MockMvc)
- [ ] ✅ 비밀번호 암호화 추가 (BCrypt 등)
- [ ] ✅ 사용자 인증/인가 로직 분리 (Spring Security 도입 고려)

---

## ⚙️ 사용 기술

- Java 17+
- Spring Boot 3+
- Spring Web / Spring Data JPA
- H2 or MySQL
- Lombok
- JPA Auditing

---

## 💡 기타

- 날짜 필드는 `BaseEntity`를 상속받아 `createdAt`, `modifiedAt` 자동 관리
- 모든 응답은 DTO 객체로 클라이언트에 반환됩니다.

---
