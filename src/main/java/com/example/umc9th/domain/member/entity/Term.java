package com.example.umc9th.domain.member.entity;

import com.example.umc9th.domain.member.enums.TermName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "term")
public class Term {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // TermName Enum의 최대 문자열 길이를 고려하여 VARCHAR(50)으로 설정 (예시)
    @Enumerated(EnumType.STRING)
    @Column(name = "name", nullable = false, columnDefinition = "VARCHAR(50)")
    private TermName name;
}
