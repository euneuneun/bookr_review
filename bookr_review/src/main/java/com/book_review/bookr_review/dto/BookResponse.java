package com.book_review.bookr_review.dto;

import com.book_review.bookr_review.entity.Book;
import lombok.Getter;

@Getter
public class BookResponse {
    private final Long bookId;
    private final String title;
    private final String author;
    private final String publisher;
    private final String description;
    private final String coverUrl;
    private final String creatorNickname;

    public BookResponse(Book book) {
        this.bookId = book.getBookId();
        this.title = book.getTitle();
        this.author = book.getAuthor();
        this.publisher = book.getPublisher();
        this.description = book.getDescription();
        this.coverUrl = book.getCoverUrl();
        this.creatorNickname = book.getCreator() != null
                ? book.getCreator().getNickname()
                : null;
    }
}
