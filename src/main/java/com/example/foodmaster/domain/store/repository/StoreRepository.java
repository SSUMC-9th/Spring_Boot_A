package com.example.foodmaster.domain.store.repository;

import com.example.foodmaster.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
