package com.book_review.bookr_review.entity;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "reviews") // DB 테이블 이름
@Getter
@Setter
@NoArgsConstructor


public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    // 어떤 유저가 썼는가
    @ManyToOne(fetch = FetchType.EAGER) // 정보를 바로 채워서 가져옵니다
    @JoinColumn(name = "user_id")
    @JsonIgnoreProperties({"password", "email", "createdAt", "hibernateLazyInitializer", "handler"})
    private User user;

    // 어떤 책에 썼는가
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler", "creator"})
    private Book book;

    private Integer rating;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt = LocalDateTime.now();

    // 기존 필드들 사이에 추가
    private String title; // 리뷰 제목 필드 추가
}