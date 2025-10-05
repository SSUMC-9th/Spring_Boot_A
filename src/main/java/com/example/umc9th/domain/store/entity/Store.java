package com.example.umc9th.domain.store.entity;

import jakarta.persistence.*;
import lombok.*;

// Lombok을 사용하여 필수 생성자 및 Getter 등을 자동 생성
@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 사용을 위한 기본 생성자
@AllArgsConstructor(access = AccessLevel.PRIVATE) // Builder 사용을 위한 전체 필드 생성자
@Getter
@Table(name = "store") // 매핑될 테이블 이름 지정
public class Store {

    // 1. 가게 식별자 (PK)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 2. 미션 식별자 (FK)
    @Column(name = "mission_id")
    private Long missionId;

    // 3. 지역 식별자 (FK)
    @Column(name = "location_id")
    private Long locationId;

    // 4. 가게명
    @Column(name = "store_name")
    private String storeName;

    // 5. 전화번호
    @Column(name = "store_number")
    private String storeNumber;

    // 6. 주소
    @Column(name = "address")
    private String address;

    /* 참고: 실제 JPA 프로젝트에서는 mission_id와 location_id 대신
    각각 Mission 엔티티와 Location 엔티티를 참조하는 @ManyToOne 관계를 설정하는 것이 표준입니다.
    */
}