# 📚 Book Review Service (bookr_review)
Spring Boot와 JPA를 활용한 도서 정보 조회 및 독자 리뷰 관리 서비스

## 📅 기간
- 2026.02.15 ~ 2026.03.09

## 📝 목차
 1. 주제
 2. 주요 기능
 3. 시스템 아키텍쳐
 4. 기술 스택
 5. 핵심 기술 및 문제 해결
 6. ERD


## 1. 주제

bookr_review는 사용자가 읽은 책에 대해 평점과 리뷰를 남기고, 다른 독자들과 정보를 공유할 수 있는 도서 리뷰 커뮤니티 플랫폼입니다.

- 도서 정보 관리: 책 제목, 저자, 출판사 및 설명 제공

- 리뷰 시스템: 평점(별점) 기반의 독자 의견 공유


## 2. 주요 기능

#### 도서 상세 정보 조회
- API를 통해 도서의 표지, 제목, 저자, 상세 설명을 실시간으로 불러옵니다.

#### 독자 리뷰 및 별점 시스템
- 각 도서별로 사용자들이 남긴 리뷰 리스트를 조회하고, 전체 평균 평점을 계산하여 표시합니다.


## 3. 시스템 아키텍쳐


## 4. 기술 스택
프론트엔드
- JavaScript (Vanilla JS), jQuery, HTML5, CSS3

백엔드
- Java 17, Spring Boot 3.x, Spring Data JPA

인프라 & DB
- MySQL 8.0, Git, GitHub


## 5. 핵심 기술 및 문제 해결 (Technical Challenges)

#### 1.보안 강화: XSS(교차 사이트 스크립팅) 공격 차단

- 문제: 리뷰 내용이나 닉네임에 악성 스크립트가 포함될 경우, 타 사용자의 브라우저에서 실행될 위험 확인

- 해결: innerHTML이나 템플릿 리터럴 대신 createElement + textContent 패턴을 적용하여 모든 사용자 데이터를 안전한 텍스트 객체로 변환


#### 2. 성능 최적화: 효율적인 DOM 조작

- 문제: 리뷰 목록 렌더링 시 반복적인 DOM 트리 탐색으로 인한 부하 발생 가능성

- 해결: 부모 요소(reviewList)를 루프 밖에서 변수로 참조하고, .empty()와 .append()를 적절히 배치하여 브라우저 렌더링 비용 최적화


## 6. ERD
<img width="893" height="630" alt="Untitled" src="https://github.com/user-attachments/assets/8001c4f4-69c2-459f-9ff4-b2cc2d6b54f7" />


