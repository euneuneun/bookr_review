package com.book_review.bookr_review.repository;

import com.book_review.bookr_review.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 이메일로 회원을 찾는 기능 추가 (JPA가 메서드 이름을 보고 SQL을 자동 생성함!)
    Optional<User> findByEmail(String email);
}