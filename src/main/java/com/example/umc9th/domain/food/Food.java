package com.example.umc9th.domain.food;

import com.example.umc9th.domain.common.BaseTimeEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "foods",
        uniqueConstraints = @UniqueConstraint(name = "uk_food_name", columnNames = "name"))
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class Food extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "food_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Column(length = 100)
    private String category;

    @OneToMany(mappedBy = "food", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserFood> userFoods = new ArrayList<>();
}

