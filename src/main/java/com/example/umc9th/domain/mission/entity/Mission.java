package com.example.umc9th.domain.mission.entity;

import com.example.umc9th.domain.store.entity.Store; // Store Entity Import 추가

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "mission")
public class Mission {

    // 1. 미션식별자 (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 2. 가게 (FK) - Store Entity를 참조하도록 수정
    // @ManyToOne: Mission(N)과 Store(1)의 관계. 미션 여러 개가 하나의 가게에 속함
    @ManyToOne(fetch = FetchType.LAZY) // 지연 로딩 설정 (성능 최적화)
    @JoinColumn(name = "store_id") // DB의 외래 키 컬럼 이름 지정
    private Store store; // Store 객체 타입으로 변경

    // 3. 포인트
    @Column(name = "point")
    private Long point;

    // 4. 내용
    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    // 5. 미션 기한
    @Column(name = "deadline")
    private LocalDateTime deadline;

    // 6. 미션 조건
    @Column(name = "condition")
    private String condition;

    // 7. 생성일자 (자동 생성)
    @CreationTimestamp
    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt;
}