package com.book_review.bookr_review.controller;

import com.book_review.bookr_review.entity.User;
import com.book_review.bookr_review.dto.LoginRequest;
import com.book_review.bookr_review.service.UserService;
import com.book_review.bookr_review.dto.PasswordChangeRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController // JSON 데이터를 주고받는 컨트롤러로 설정
@RequestMapping("/api/users") // 이 주소로 들어오는 요청은 다 여기서 처리함
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PutMapping("/{userId}/password") // 이 주소로 데이터를 수정(PUT)하겠다는 신호가 오면 이 메서드를 실행시켜라
    public ResponseEntity<?> changePassword(@PathVariable Long userId, @RequestBody PasswordChangeRequest request){
        boolean isChanged = userService.updatePassword(userId, request.getCurrentPassword(),request.getNewPassword());

        if (isChanged){
            return ResponseEntity.ok("비밀번호가 성공적으로 변경되었습니다.");
        }else{
            return ResponseEntity.status(400).body("현재 비밀번호가 일치하지 않습니다.");
        }

    }


    // 회원가입 요청 받기 (POST 방식)
    @PostMapping("/join")
    public String join(@RequestBody User user) {
        try {
            userService.join(user);
            return "회원가입 성공!";
        } catch (IllegalStateException e) {
            return e.getMessage(); // "이미 존재하는 회원입니다." 메시지 반환
        }
    }

    // 로그인 요청 받기 (POST 방식)
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest dto) {
        User user = userService.login(dto.getEmail(), dto.getPassword());
        if (user != null) {
            // "환영합니다" 문자열 대신 유저 객체 자체를 반환합니다.
            // 이렇게 하면 JSON 안에 userId, nickname, email 등이 모두 포함됩니다.
            return ResponseEntity.ok(user);
        } else {
            return ResponseEntity.status(401).body("이메일 또는 비밀번호가 틀렸습니다.");
        }
    }



}