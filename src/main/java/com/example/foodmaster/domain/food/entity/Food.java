package com.example.foodmaster.domain.food.entity;

import com.example.foodmaster.domain.food.enums.FoodType;
import com.example.foodmaster.domain.member.entity.mapping.MemberFood;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
@Table(name = "food")
public class Food {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "food_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private FoodType foodType;

    @OneToMany(mappedBy = "food", cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoodList = new ArrayList<>() ;

}
