package com.book_review.bookr_review.controller;

import com.book_review.bookr_review.entity.User;
import com.book_review.bookr_review.dto.LoginRequest;
import com.book_review.bookr_review.service.UserService;
import com.book_review.bookr_review.dto.PasswordChangeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.book_review.bookr_review.dto.LoginResponse;



@RestController // JSON 데이터를 주고받는 컨트롤러로 설정
@RequestMapping("/api/users") // 이 주소로 들어오는 요청은 다 여기서 처리함
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{userId}/password")
    public ResponseEntity<?> changePassword(@PathVariable Long userId, @RequestBody PasswordChangeRequest request){
        try {
            boolean isChanged = userService.updatePassword(userId,
                    request.getCurrentPassword(), request.getNewPassword());
            if (isChanged) {
                return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
            } else {
                return ResponseEntity.status(400).body("현재 비밀번호가 일치하지 않습니다.");
            }
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(404).body("존재하지 않는 사용자입니다.");
        }
    }


    // 회원가입 요청 받기 (POST 방식)
    @PostMapping
    public ResponseEntity<String> join(@RequestBody User user) {
        try {
            userService.join(user);
            return ResponseEntity.status(201).body("회원가입 성공!");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    // 로그인 요청 받기 (POST 방식)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest dto) {
        User user = userService.login(dto.getEmail(), dto.getPassword());
        if (user != null) {
            LoginResponse response = new LoginResponse(user.getUserId(), user.getNickname(), user.getEmail());
            return ResponseEntity.ok(response);
        } else {
            return ResponseEntity.status(401).body("이메일 또는 비밀번호가 틀렸습니다.");
        }
    }



}