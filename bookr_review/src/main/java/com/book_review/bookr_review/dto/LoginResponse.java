package com.book_review.bookr_review.dto;

import lombok.Getter;

@Getter
public class LoginResponse {
    private final Long id;
    private final String nickname;
    private final String email;

    public LoginResponse(Long id, String nickname, String email) {
        this.id = id;
        this.nickname = nickname;
        this.email = email;
    }
}