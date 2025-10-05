package com.example.umc9th.domain.member.entity;


import com.example.umc9th.domain.member.enums.Gender;
import com.example.umc9th.global.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // 기본 생성자 (JPA 필수)
@AllArgsConstructor(access = AccessLevel.PRIVATE) // 모든 필드를 포함하는 생성자
@Getter
@Table(name = "member") // 매핑될 테이블 이름 지정
public class Member extends BaseEntity {

    // 1. 고유 ID 필드
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // DB가 ID를 자동 생성 (Auto Increment)
    private Long id;

    // 2. 이름 필드
    @Column(name = "name", length = 3, nullable = false)
    private String name;

    // 3. 성별 필드
    @Column(name = "gender", length = 3, nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

}