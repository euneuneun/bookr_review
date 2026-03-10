package com.book_review.bookr_review.controller;

import com.book_review.bookr_review.entity.Book;
import com.book_review.bookr_review.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import com.book_review.bookr_review.dto.BookResponse;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public List<BookResponse> getAllBooks() {
        return bookService.findAllBooks()
                .stream()
                .map(BookResponse::new)
                .toList();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponse> getBook(@PathVariable Long id) {
        Book book = bookService.findById(id);
        if (book == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new BookResponse(book));
    }

    // ⭐ 수정된 부분: 이제 bookService에게 검색을 부탁합니다.
    @GetMapping("/search")
    public List<BookResponse> searchBooks(@RequestParam String keyword) {
        if (keyword == null || keyword.trim().isEmpty()) {
            return List.of();
        }
        return bookService.searchBooks(keyword)
                .stream()
                .map(BookResponse::new)
                .toList();
    }
}