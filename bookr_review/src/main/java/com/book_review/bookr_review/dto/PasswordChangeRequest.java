package com.book_review.bookr_review.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class PasswordChangeRequest {
    private String currentPassword; // 현재 비번(확인용)
    private String newPassword; // 바꿀 비번
}
