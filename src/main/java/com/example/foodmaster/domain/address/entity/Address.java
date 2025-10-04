package com.example.foodmaster.domain.address.entity;


import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "address")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "address_line", nullable = false)
    private String addressLine;

    @OneToMany(mappedBy = "address",fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
    private List<DetailAddress> detailAddressList = new ArrayList<>();

}
