package com.book_review.bookr_review.service;

import com.book_review.bookr_review.entity.Book;
import com.book_review.bookr_review.entity.Heart;
import com.book_review.bookr_review.entity.User;
import com.book_review.bookr_review.repository.BookRepository;
import com.book_review.bookr_review.repository.HeartRepository;
import com.book_review.bookr_review.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HeartService {

    private final HeartRepository heartRepository;
    private final UserRepository userRepository;
    private final BookRepository bookRepository;

    @Transactional
    public boolean toggleHeart(Long userId, Long bookId) {
        User user = userRepository.findById(userId).orElseThrow();
        Book book = bookRepository.findById(bookId).orElseThrow();

        // 이미 좋아요를 눌렀는지 확인
        Optional<Heart> heart = heartRepository.findByUserAndBook(user, book);

        if (heart.isPresent()) {
            // 이미 있다면 삭제 (좋아요 취소)
            heartRepository.delete(heart.get());
            return false;
        } else {
            // 없다면 생성 (좋아요 완료)
            heartRepository.save(Heart.builder().user(user).book(book).build());
            return true;
        }
    }
}
