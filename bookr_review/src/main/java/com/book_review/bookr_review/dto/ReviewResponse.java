package com.book_review.bookr_review.dto;

import com.book_review.bookr_review.entity.Review;
import lombok.Getter;
import java.time.LocalDateTime;

@Getter
public class ReviewResponse {
    private final Long reviewId;
    private final String title;
    private final String content;
    private final Integer rating;
    private final LocalDateTime createdAt;

    // User에서는 닉네임만
    private final String nickname;

    // Book에서는 제목만
    private final String bookTitle;

    public ReviewResponse(Review review) {
        this.reviewId = review.getReviewId();
        this.title = review.getTitle();
        this.content = review.getContent();
        this.rating = review.getRating();
        this.createdAt = review.getCreatedAt();
        this.nickname = review.getUser() != null
                ? review.getUser().getNickname() : "익명";
        this.bookTitle = review.getBook() != null
                ? review.getBook().getTitle() : "알 수 없는 도서";
    }
}