package com.example.umc9th.domain.mission.repository;

import com.example.umc9th.domain.mission.entity.Mission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import com.example.umc9th.domain.store.entity.Store;
public interface MissionRepository extends JpaRepository<Mission, Long> {

    /**
     * 특정 가게(Store)의 미션 목록을 페이징 처리하여 조회합니다.
     * JPQL 메서드 쿼리: findAllBy[Store] + Pageable
     */
    Page<Mission> findAllByStore(Store store, Pageable pageable);
}