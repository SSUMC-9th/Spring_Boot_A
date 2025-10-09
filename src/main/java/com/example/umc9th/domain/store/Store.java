package com.example.umc9th.domain.store;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.mission.Mission;
import com.example.umc9th.domain.region.Region;
import com.example.umc9th.domain.review.Review;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "stores",
        indexes = {
                @Index(name = "idx_stores_region", columnList = "region_id"),
                @Index(name = "idx_stores_name", columnList = "name")
        })
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class Store extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "store_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "region_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_stores_region"))
    private Region region;

    @Column(length = 150, nullable = false)
    private String name;

    @Column(length = 255)
    private String address;

    @Column(length = 100)
    private String managerName;

    @Column(length = 30)
    private String managerPhone;

    @OneToMany(mappedBy = "store")
    private List<Mission> missions = new ArrayList<>();

    @OneToMany(mappedBy = "store")
    private List<Review> reviews = new ArrayList<>();
}

