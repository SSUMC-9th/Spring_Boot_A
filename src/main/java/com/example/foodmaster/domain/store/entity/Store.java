package com.example.foodmaster.domain.store.entity;

import com.example.foodmaster.domain.address.entity.DetailAddress;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "store")
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "store_number", nullable = false)
    private String storeNumber;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "detail_address_id")
    private DetailAddress detailAddress;
}
