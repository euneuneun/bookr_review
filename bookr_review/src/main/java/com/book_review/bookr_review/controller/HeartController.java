package com.book_review.bookr_review.controller;

import com.book_review.bookr_review.service.HeartService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/hearts")
@RequiredArgsConstructor
public class HeartController {

    private final HeartService heartService;

    // 좋아요 토글 (누르면 생기고, 다시 누르면 없어짐)
    @PostMapping("/{bookId}")
    public ResponseEntity<Boolean> toggleHeart(@PathVariable Long bookId, @RequestParam Long userId) {
        // 서비스의 toggleHeart를 여기서 호출합니다!
        // 이제 "사용되지 않습니다" 문구가 사라질 거예요.
        boolean isHearted = heartService.toggleHeart(userId, bookId);
        return ResponseEntity.ok(isHearted);
    }
}