package com.book_review.bookr_review.repository;

import com.book_review.bookr_review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {

    // 유저 ID로 리뷰 찾기
    List<Review> findByUser_UserId(Long userId);

    // ★ 이 줄이 꼭 있어야 상세페이지 404 에러가 사라집니다!
    List<Review> findByBook_BookId(Long bookId);


}