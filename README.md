# 🗓️ 일정 관리 프로그램 (Schedule Management App)

스프링 부트를 활용한 **일정 관리 백엔드 프로그램**입니다.  
일정 생성, 수정, 삭제는 물론 사용자 CRUD 및 회원가입 기능까지 구현되어 있습니다.

---

## 📌 ERD 설계

```
[User] 1 : N [Schedule]

┌─────────────┐          ┌────────────────────┐
│   User      │          │     Schedule       │
├─────────────┤          ├────────────────────┤
│ id (PK)     │◄────────►│ user_id (FK)       │
│ name        │          │ id (PK)            │
│ email       │          │ title              │
│ password    │          │ content            │
│ createdAt   │          │ createdAt          │
│ modifiedAt  │          │ modifiedAt         │
└─────────────┘          └────────────────────┘
```

---

## 🔐 API 명세서

| 기능 | Method | URL | Request Body | Response Body | 상태코드 |
|------|--------|-----|---------------|----------------|-----------|
| 회원가입 | POST | /users/signup | {name, email, password} | {id, name, email, createdAt, modifiedAt} | 201 |
| 비밀번호 수정 | PATCH | /users/{id} | {password} | {id, name, email} | 200 |
| 유저 전체 조회 | GET | /users | - | [ {id, name, email} ] | 200 |
| 유저 삭제 | DELETE | /users/{id} | - | - | 200 |
| 일정 생성 | POST | /schedules | {title, content, userId} | {id, title, content, createdAt} | 201 |
| 일정 전체 조회 | GET | /schedules | - | [ {id, title, content, createdAt} ] | 200 |
| 일정 상세 조회 | GET | /schedules/{id} | - | {id, title, content, createdAt} | 200 |
| 일정 수정 | PATCH | /schedules/{id} | {title, content, password} | {title, content} | 200 |
| 일정 삭제 | DELETE | /schedules/{id}?password=xxx | - | - | 200 |

---

## ✨ 향후 개선 포인트

- [ ] 유효성 검사(@Valid, @NotBlank 등) 적용
- [ ] 비밀번호 암호화 적용 (Spring Security + BCrypt)
- [ ] 예외 처리 일괄 적용 (@ControllerAdvice)
- [ ] JWT 로그인 인증 기능 확장
- [ ] 단위 테스트 및 통합 테스트 코드 작성
