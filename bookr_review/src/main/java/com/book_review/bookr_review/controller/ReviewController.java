package com.book_review.bookr_review.controller;

import com.book_review.bookr_review.entity.Review;
import com.book_review.bookr_review.service.ReviewService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("/write")
    public String write(@RequestBody Review review) {
        reviewService.writeReview(review);
        return "리뷰가 성공적으로 등록되었습니다!";
    }

    // ReviewController.java 안에 추가
    @GetMapping("/my/{userId}")
    public List<Review> getMyReviews(@PathVariable Long userId) {
        return reviewService.getMyReviews(userId);
    }

    // 특정 도서의 모든 리뷰를 가져오는 메서드 추가
    @GetMapping("/book/{bookId}")
    public List<Review> getReviewsByBookId(@PathVariable Long bookId) {
        return reviewService.getReviewsByBookId(bookId);
    }

    @PutMapping("/{reviewId}")
    public String update(@PathVariable Long reviewId, @RequestBody Review review) {
        reviewService.updateReview(reviewId, review.getTitle(), review.getContent(), review.getRating());
        return "리뷰가 수정되었습니다!";
    }

    @GetMapping("/{id}")
    public ResponseEntity<Review> getReview(@PathVariable Long id) {
        Review review = reviewService.findById(id);
        return ResponseEntity.ok(review);
    }
}