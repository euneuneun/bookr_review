package com.book_review.bookr_review.repository;

import com.book_review.bookr_review.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
    // 제목이나 저자에 키워드가 포함된 책을 찾는 쿼리
    List<Book> findByTitleContainingOrAuthorContaining(String title, String author);
}