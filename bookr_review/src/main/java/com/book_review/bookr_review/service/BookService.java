package com.book_review.bookr_review.service;

import com.book_review.bookr_review.entity.Book;
import com.book_review.bookr_review.repository.BookRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Transactional(readOnly = true)
    public List<Book> findAllBooks() {
        return bookRepository.findAll();
    }

    // BookService.java

    public Book findById(Long id) {
        // 1. 리포지토리에서 ID로 책을 찾습니다.
        // 2. 만약 없으면(orElseThrow) 에러를 던집니다.
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("해당 ID의 책을 찾을 수 없습니다: " + id));
    }

    // BookService.java 파일 안의 클래스 내부에 추가
    // BookService 클래스 안에 아래 내용을 추가하세요
    public List<Book> searchBooks(String keyword) {
        // 여기서 실제로 레포지토리를 사용합니다.
        return bookRepository.findByTitleContainingOrAuthorContaining(keyword, keyword);
    }
}