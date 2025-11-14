package com.example.umc9th.domain.member.entity;

import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 사용을 위한 기본 생성자
@AllArgsConstructor(access = AccessLevel.PRIVATE) // Builder 사용을 위한 전체 필드 생성자
@Getter
@Table(name = "member") // 매핑될 테이블 이름 지정
public class Member extends BaseEntity {

    // 1. 유저 식별자 (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 2. 이름
    @Column(name = "name", nullable = false)
    private String name;

    // 3. 전화번호 (DB 컬럼명: phone_number)
    @Column(name = "phone_number")
    private String phoneNumber;

    // 4. 이메일
    @Column(name = "email")
    private String email;

    // 5. 주소
    @Column(name = "address")
    private String address;

    // 6. 닉네임
    @Column(name = "nickname")
    private String nickname;

    // 7. 포인트
    @Column(name = "point")
    private Long point;

    // 8. 상태 (VARCHAR -> String, 실제로는 Enum 사용 권장)
    @Column(name = "status")
    private String status;

    // 9. 비활성화 일자
    @Column(name = "inactive_date")
    private LocalDateTime inactiveDate;


}