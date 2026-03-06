package com.book_review.bookr_review.service;

import com.book_review.bookr_review.entity.User;
import com.book_review.bookr_review.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // 생성자 주입: 리모컨(Repository)을 스프링으로부터 전달받음
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * 회원가입 기능
     */
    @Transactional
    public Long join(User user) {
        // 중복 회원 검증 (이미 있는 이메일인지 확인)
        validateDuplicateUser(user);

        userRepository.save(user);
        return user.getUserId();
    }

    private void validateDuplicateUser(User user) {
        // DB에서 해당 이메일로 찾았을 때 결과가 존재하면 에러 발생
        userRepository.findByEmail(user.getEmail())
                .ifPresent(u -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }

    /**
     * 로그인 기능
     */
    public User login(String email, String password) {
        // 1. 이메일로 회원 조회
        return userRepository.findByEmail(email)
                .filter(u -> u.getPassword().equals(password)) // 2. 비밀번호 일치 확인
                .orElseThrow(() -> new IllegalStateException("이메일 또는 비밀번호가 맞지 않습니다."));
    }

    /* 비밀번호 변경 */
    @Transactional // (자동 저장 도우미)
    public boolean updatePassword(Long userId, String currentPwd, String newPwd){
        // 2. 유저 조회
        User user = userRepository.findById(userId) // 유저 찾기 "이 번호(ID)를 가진 유저가 우리 DB에 있어? 라고 물어보느 단계,
                .orElseThrow(()->new IllegalArgumentException("존재하지 않는 사용자입니다.")); // 만약 없다면 .orElseThrow(...)가 실행되어 "존재하지 않는 사용자입니다."라는 경고와 함께 작업을 중단함

        if (user.getPassword().equals(currentPwd)){
            user.setPassword(newPwd); // 새 비밀번호 설정
            return true;
        }

        return false;
    }



}