package com.example.foodmaster.domain.food.repository;

import com.example.foodmaster.domain.food.entity.Food;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FoodRepository extends JpaRepository<Food, Long> {
}
