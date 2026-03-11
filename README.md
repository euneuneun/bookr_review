# 📚 Book Review Service (bookr_review)

Spring Boot와 JPA를 활용한 도서 정보 조회 및 독자 리뷰 관리 서비스

<br>

## 📅 개발 기간
- 2026.02.01 ~ 2026.03.09

<br>

## 📝 목차
1. [주제](#1-주제)
2. [주요 기능](#2-주요-기능)
3. [시스템 아키텍처](#3-시스템-아키텍처)
4. [기술 스택](#4-기술-스택)
5. [핵심 기술 및 문제 해결](#5-핵심-기술-및-문제-해결-technical-challenges)
6. [ERD](#6-erd)

<br>

---

## 1. 주제

**bookr_review**는 사용자가 읽은 책에 대해 평점과 리뷰를 남기고, 다른 독자들과 독서 경험을 공유할 수 있는 **도서 리뷰 커뮤니티 플랫폼**입니다.

- **도서 정보 관리:** 책 제목, 저자, 출판사, 장르, 표지 이미지 및 상세 설명 제공
- **리뷰 시스템:** 평점(별점 1~5) 기반의 독자 의견 작성 및 공유
- **회원 시스템:** 회원가입/로그인을 통한 개인화된 서비스 이용
- **좋아요 기능:** 마음에 드는 도서에 좋아요(하트)를 남겨 관심 도서 표시
- **마이페이지:** 내가 작성한 리뷰 목록 확인, 수정, 삭제

<br>

---

## 2. 주요 기능

### 📖 도서 목록 조회 및 검색
- 전체 도서 목록을 카드 형태로 표시합니다.
- 책 제목 또는 저자명으로 도서를 실시간 검색할 수 있습니다.
- `BookRepository`의 `findByTitleContainingOrAuthorContaining` 메서드를 활용한 키워드 기반 검색을 지원합니다.

<img width="1167" height="892" alt="image" src="https://github.com/user-attachments/assets/4697464f-67d7-43d5-91c5-74cdf76d9792" />


<br>

### 📄 도서 상세 정보 조회
- API를 통해 도서의 표지 이미지, 제목, 저자, 출판사, 상세 설명을 실시간으로 불러옵니다.
- 해당 도서에 달린 모든 리뷰 목록과 독자 평균 평점을 함께 표시합니다.
- 좋아요(하트) 버튼으로 관심 도서를 표시할 수 있습니다.

<img width="935" height="897" alt="도서 상세" src="https://github.com/user-attachments/assets/3551efaf-ce22-4436-ba75-d6c9e31ee920" />

<br>

### ⭐ 독자 리뷰 및 별점 시스템
- 로그인한 사용자는 각 도서에 별점(1~5점)과 리뷰 제목, 감상평을 작성할 수 있습니다.
- 도서 상세 페이지에서 전체 리뷰 목록과 평균 평점을 확인할 수 있습니다.
- 작성한 리뷰는 마이페이지에서 수정 및 삭제가 가능합니다.

<img width="543" height="828" alt="image" src="https://github.com/user-attachments/assets/245cd32e-7f37-4e73-9dce-7978e984533a" />

<br>

### ❤️ 좋아요(Heart) 기능
- 도서 상세 페이지에서 좋아요 버튼을 클릭하면 해당 도서에 좋아요를 추가합니다.
- 같은 버튼을 다시 클릭하면 좋아요가 취소되는 **토글 방식**으로 동작합니다.
- 비로그인 상태에서는 로그인 페이지로 자동 이동합니다.

<br>

### 👤 회원 시스템 (회원가입 / 로그인 / 비밀번호 변경)
- 이메일과 비밀번호, 닉네임으로 회원가입이 가능합니다.
- 중복 이메일 가입을 방지하는 서버 측 유효성 검사가 적용되어 있습니다.
- 로그인 성공 시 사용자 정보를 `sessionStorage`에 저장하여 페이지 간 상태를 유지합니다.
- 마이페이지 > 계정 설정에서 현재 비밀번호 확인 후 새 비밀번호로 변경할 수 있습니다.

<img width="451" height="563" alt="image" src="https://github.com/user-attachments/assets/d9f443c7-3d57-4885-b980-9bdad71bd644" />

<img width="442" height="488" alt="image" src="https://github.com/user-attachments/assets/6eba4048-f10e-4baa-a7a5-16fb19be197d" />


<img width="443" height="665" alt="image" src="https://github.com/user-attachments/assets/652f32c6-8225-4882-b081-5c8348ed42d0" />

<br>

### 🗂️ 마이페이지 (내 리뷰 관리)
- 로그인한 사용자가 작성한 모든 리뷰를 한눈에 확인할 수 있습니다.
- 각 리뷰 카드의 메뉴(⋯)를 통해 **수정** 및 **삭제**가 가능합니다.
- 작성한 리뷰 수를 통계 카드 형태로 표시합니다.

<img width="1297" height="852" alt="image" src="https://github.com/user-attachments/assets/0f3abb48-8191-421c-b8ac-fbf845e402a9" />

<br>

---

## 3. 시스템 아키텍처

```
[Client Layer]
    브라우저 (HTML, CSS, Vanilla JS, jQuery)
        │  HTTP 요청 (REST API)
        ▼
[Presentation Layer]
    Controller
    ├── UserController     → /api/users
    ├── BookController     → /api/books
    ├── ReviewController   → /api/reviews
    └── HeartController    → /api/hearts
        │
        ▼
[Business Layer]
    Service
    ├── UserService        → 회원가입, 로그인, 비밀번호 변경
    ├── BookService        → 도서 조회, 검색
    ├── ReviewService      → 리뷰 작성, 조회, 수정
    └── HeartService       → 좋아요 토글
        │
        ▼
[Data Access Layer]
    Repository (Spring Data JPA)
    ├── UserRepository
    ├── BookRepository
    ├── ReviewRepository
    └── HeartRepository
        │
        ▼
[Database]
    MySQL 8.0
    ├── users
    ├── books
    ├── reviews
    └── heart
```

> **요청 흐름 예시 (리뷰 작성)**
> 1. 사용자가 `review.html`에서 별점과 내용을 입력 후 제출
> 2. `POST /api/reviews` 요청이 `ReviewController`로 전달
> 3. `ReviewController` → `ReviewService.writeReview()` 호출
> 4. `ReviewService` → `ReviewRepository.save()` 호출로 DB에 저장
> 5. `201 Created` 응답 반환 후 마이페이지로 이동

<br>

---

## 4. 기술 스택

### Frontend
| 기술 | 설명 |
|------|------|
| HTML5 / CSS3 | 마크업 및 스타일링 |
| JavaScript (Vanilla JS) | 인터랙션 및 API 통신 (fetch API) |
| jQuery 3.6 | 도서 상세 페이지 AJAX 통신 |

### Backend
| 기술 | 버전 | 설명 |
|------|------|------|
| Java | 17 | 주 개발 언어 |
| Spring Boot | 3.x | 웹 애플리케이션 프레임워크 |
| Spring Data JPA | - | ORM 기반 데이터 접근 계층 |
| Lombok | - | 보일러플레이트 코드 제거 |
| Spring Validation | - | 요청 데이터 유효성 검사 |

### Database & Infra
| 기술 | 버전 | 설명 |
|------|------|------|
| MySQL | 8.0 | 관계형 데이터베이스 |
| Git / GitHub | - | 버전 관리 및 코드 호스팅 |

<br>

---

## 5. 핵심 기술 및 문제 해결 (Technical Challenges)

### 1. 보안 강화: XSS(교차 사이트 스크립팅) 공격 차단

**문제**

리뷰 내용이나 닉네임 등 사용자가 입력한 데이터를 `innerHTML` 또는 템플릿 리터럴(`${}`)로 DOM에 직접 삽입할 경우, 악의적인 사용자가 `<script>` 태그를 입력하면 다른 사용자의 브라우저에서 스크립트가 실행되는 XSS 취약점이 발생할 수 있습니다.

**해결**

`createElement` + `textContent` 패턴으로 전환하여 모든 사용자 입력 데이터를 순수 텍스트로 처리했습니다. 이 방식은 브라우저가 내용을 HTML이 아닌 문자열로 인식하므로 스크립트 실행이 원천 차단됩니다.

```javascript
// ❌ 취약한 방식 (XSS 위험)
bookCard.innerHTML = `<h3>${book.title}</h3>`;

// ✅ 안전한 방식 (XSS 차단)
const title = document.createElement('h3');
title.textContent = book.title; // 스크립트가 있어도 텍스트로만 처리됨
bookCard.appendChild(title);
```

<br>

### 2. 성능 최적화: 효율적인 DOM 조작

**문제**

리뷰 목록처럼 반복적으로 DOM 요소를 추가할 때, 매 반복마다 부모 요소를 `document.getElementById()`로 탐색하면 불필요한 DOM 트리 순회가 발생합니다. 또한 반복문 안에서 `innerHTML +=` 패턴을 사용하면 기존 내용을 매번 파싱·재렌더링하여 성능 저하가 생깁니다.

**해결**

부모 요소를 루프 밖에서 변수로 한 번만 참조하고, `.empty()` + `.append()` 방식을 적절히 배치하여 불필요한 렌더링 비용을 줄였습니다.

```javascript
// ✅ 개선된 방식
const listDiv = document.getElementById('myReviewList'); // 루프 밖에서 한 번만 참조
listDiv.innerHTML = ''; // 초기화

reviews.forEach(review => {
    const card = document.createElement('div');
    // ... 카드 구성
    listDiv.appendChild(card); // 완성된 요소를 한 번에 추가
});
```

<br>

### 3. 순환 참조 방지: `@JsonIgnoreProperties` 적용

**문제**

`Review → User → Review` 또는 `Review → Book → Creator(User)` 처럼 JPA 연관 엔티티를 JSON으로 직렬화할 때, 양방향 참조로 인해 무한 루프가 발생하여 `StackOverflowError`가 생길 수 있습니다.

**해결**

`@JsonIgnoreProperties`를 연관 관계 필드에 선언하여 직렬화 시 불필요하거나 순환을 유발하는 필드를 명시적으로 제외했습니다.

```java
// Review.java
@ManyToOne(fetch = FetchType.EAGER)
@JoinColumn(name = "user_id")
@JsonIgnoreProperties({"password", "email", "createdAt", "hibernateLazyInitializer", "handler"})
private User user;

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "book_id")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "creator"})
private Book book;
```

또한 민감 정보(password, email)도 함께 제외하여 API 응답에서 불필요한 개인정보가 노출되지 않도록 처리했습니다.

<br>

### 4. DTO 분리로 엔티티 직접 노출 방지

**문제**

Controller에서 JPA 엔티티를 그대로 반환하면 DB 구조가 API 응답에 그대로 노출되고, 연관 엔티티의 Lazy Loading 시 `LazyInitializationException`이 발생할 위험이 있습니다. 또한 클라이언트에 불필요한 데이터가 과도하게 전달됩니다.

**해결**

`BookResponse`, `ReviewResponse` 등의 DTO 클래스를 별도로 만들어 필요한 데이터만 선별해서 반환했습니다.

```java
// BookResponse.java - 엔티티 대신 DTO로 응답
public class BookResponse {
    private final Long bookId;
    private final String title;
    private final String author;
    private final String creatorNickname; // creator 엔티티 전체 대신 닉네임만

    public BookResponse(Book book) {
        this.creatorNickname = book.getCreator() != null
                ? book.getCreator().getNickname() : null;
        // ...
    }
}
```

<br>

### 5. JPA Fetch 전략 설계: EAGER vs LAZY 분리

**문제**

연관 관계에서 Fetch 타입을 일괄 `EAGER`로 설정하면 불필요한 데이터까지 항상 함께 조회되어 성능이 저하되고, 반대로 모두 `LAZY`로 설정하면 View/Controller 계층에서 세션이 닫힌 후 접근 시 `LazyInitializationException`이 발생합니다.

**해결**

사용 빈도와 필요성을 고려해 연관 관계별로 Fetch 전략을 다르게 적용했습니다.

```java
// Review.java
// User: 리뷰 조회 시 작성자 정보가 항상 필요하므로 EAGER
@ManyToOne(fetch = FetchType.EAGER)
private User user;

// Book: DTO에서 처리하거나 필요할 때만 접근하므로 LAZY
@ManyToOne(fetch = FetchType.LAZY)
private Book book;
```

<br>

### 6. 좋아요 토글 로직: 중복 없는 단일 API 설계

**문제**

좋아요 추가와 취소를 별도의 API로 분리하면 클라이언트와 서버 간 상태 동기화가 복잡해집니다.

**해결**

단일 `POST /api/hearts/{bookId}` 엔드포인트에서 이미 좋아요 데이터가 존재하면 삭제, 없으면 생성하는 **토글 방식**으로 구현하여 API를 단순화했습니다.

```java
// HeartService.java
public boolean toggleHeart(Long userId, Long bookId) {
    Optional<Heart> heart = heartRepository.findByUserAndBook(user, book);

    if (heart.isPresent()) {
        heartRepository.delete(heart.get()); // 이미 있으면 삭제 (취소)
        return false;
    } else {
        heartRepository.save(Heart.builder().user(user).book(book).build()); // 없으면 생성
        return true;
    }
}
```

<br>

---

## 6. ERD

<img width="983" height="630" alt="ERD" src="https://github.com/user-attachments/assets/d2b72d69-35c5-46a9-9272-fb5f1c5e6785" />

### 테이블 설명

| 테이블 | 설명 |
|--------|------|
| `users` | 회원 정보 (이메일, 비밀번호, 닉네임, 가입일) |
| `books` | 도서 정보 (제목, 저자, 출판사, 장르, 표지 URL, 등록자) |
| `reviews` | 리뷰 정보 (제목, 내용, 별점, 작성일, 수정일) — users, books와 N:1 관계 |
| `heart` | 좋아요 정보 — users, books와 N:1 관계, (user_id + book_id) 기준 중복 방지 |

<br>

---

## 📁 프로젝트 구조

```
bookr_review/
├── src/main/java/com/book_review/bookr_review/
│   ├── controller/
│   │   ├── BookController.java
│   │   ├── ReviewController.java
│   │   ├── UserController.java
│   │   └── HeartController.java
│   ├── service/
│   │   ├── BookService.java
│   │   ├── ReviewService.java
│   │   ├── UserService.java
│   │   └── HeartService.java
│   ├── repository/
│   │   ├── BookRepository.java
│   │   ├── ReviewRepository.java
│   │   ├── UserRepository.java
│   │   └── HeartRepository.java
│   ├── entity/
│   │   ├── Book.java
│   │   ├── Review.java
│   │   ├── User.java
│   │   └── Heart.java
│   └── dto/
│       ├── BookResponse.java
│       ├── ReviewResponse.java
│       ├── LoginRequest.java
│       ├── LoginResponse.java
│       └── PasswordChangeRequest.java
└── src/main/resources/
    ├── static/
    │   ├── index.html          (메인 - 도서 목록)
    │   ├── book-detail.html    (도서 상세)
    │   ├── review.html         (리뷰 작성)
    │   ├── edit-review.html    (리뷰 수정)
    │   ├── account-settings.html (마이페이지)
    │   ├── change-password.html  (비밀번호 변경)
    │   ├── login.html          (로그인)
    │   └── join.html           (회원가입)
    └── application.properties
```



