package com.book_review.bookr_review.entity; // 패키지 경로가 실제와 맞는지 꼭 확인!

// 여기서부터 "import" 부분이 도구를 가져오는 과정입니다. 이 부분이 꼭 있어야 해요!
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
public class User { // 파일 이름이 Users.java이면 여기도 Users여야 합니다!

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String email;
    private String password;
    private String nickname;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();
}