package com.book_review.bookr_review.repository;

import com.book_review.bookr_review.entity.Book;
import com.book_review.bookr_review.entity.Heart;
import com.book_review.bookr_review.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface HeartRepository extends JpaRepository<Heart, Long> {
    // 유저와 책 정보를 기반으로 하트가 있는지 찾는 쿼리 메서드
    Optional<Heart> findByUserAndBook(User user, Book book);
}
