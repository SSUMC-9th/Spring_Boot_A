package com.example.umc9th.infrastructure.store;

import com.example.umc9th.domain.store.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StoreJpaRepository extends JpaRepository<Store, Long> {
}
