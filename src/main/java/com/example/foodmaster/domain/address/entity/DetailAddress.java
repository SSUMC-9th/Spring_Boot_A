package com.example.foodmaster.domain.address.entity;

import com.example.foodmaster.domain.member.entity.Member;
import com.example.foodmaster.domain.store.entity.Store;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "detail_address")
public class DetailAddress {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "detail_address_line", nullable = false)
    private String detailAddressLine;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToOne(mappedBy = "detailAddress", fetch = FetchType.LAZY)
    private Member member;

    @OneToOne(mappedBy = "detailAddress", fetch = FetchType.LAZY)
    private Store store;
}
