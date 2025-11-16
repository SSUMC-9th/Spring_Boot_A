package com.example.umc9th.domain.food.repository;

// Food 엔티티의 정확한 경로를 사용해야 합니다.
import com.example.umc9th.domain.food.entity.Food;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
    // Spring Data JPA가 이 인터페이스를 보고 자동으로 Bean을 생성합니다.
}