package com.book_review.bookr_review.service;



import com.book_review.bookr_review.entity.Review;
import com.book_review.bookr_review.repository.ReviewRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ReviewService {
    private final ReviewRepository reviewRepository;

    public ReviewService(ReviewRepository reviewRepository) {
        this.reviewRepository = reviewRepository;
    }

    @Transactional
    public void writeReview(Review review) {
// 리뷰 저장 (실제로는 여기서 별점 제한 등의 로직을 넣을 수 있어요)
        reviewRepository.save(review);
    }



// ReviewService.java 안에 추가
    @Transactional(readOnly = true) // 읽기 전용으로 성능 최적화
    public List<Review> getMyReviews(Long userId) {
        return reviewRepository.findByUser_UserId(userId);
    }
    // ReviewService.java 안에서
    @Transactional(readOnly = true)
    public List<Review> getReviewsByBookId(Long bookId) {
        // 여기서 repository의 메서드를 '사용'하게 됩니다!
        return reviewRepository.findByBook_BookId(bookId);
    }

    @Transactional
    public void updateReview(Long reviewId, String newTitle, String newContent, Integer newRating) {
        // 1. 수정할 리뷰를 DB에서 찾습니다.
        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰가 존재하지 않습니다."));

        // 2. 새로운 데이터로 덮어씌웁니다.
        review.setTitle(newTitle);
        review.setContent(newContent);
        review.setRating(newRating);
        review.setUpdatedAt(java.time.LocalDateTime.now()); // 수정 시간 업데이트
        // @Transactional 덕분에 따로 save()를 안 해도 메서드가 끝나면 자동 저장됩니다!
    }

    public Review findById(Long id){
        return reviewRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("해당 리뷰를 찾을 수 없습니다. id="+id));
    }



}

