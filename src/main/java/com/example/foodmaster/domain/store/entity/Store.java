package com.example.foodmaster.domain.store.entity;

import com.example.foodmaster.domain.address.entity.DetailAddress;
import com.example.foodmaster.domain.mission.entity.Mission;
import com.example.foodmaster.domain.review.entity.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

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

    @OneToOne()
    @JoinColumn(name = "detail_address_id")
    private DetailAddress detailAddress;

    @OneToMany(mappedBy = "store")
    List<Mission> missionList = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    List<Review> reviewList = new ArrayList<>();
}
