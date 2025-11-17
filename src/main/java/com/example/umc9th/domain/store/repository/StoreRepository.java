package com.example.umc9th.domain.store.repository;

import com.example.umc9th.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository // 선택 사항이지만 명시하는 것이 좋습니다.
public interface StoreRepository extends JpaRepository<Store, Long> {

    // JpaRepository를 상속하면 아래와 같은 메서드를 자동으로 사용할 수 있습니다:
     Store save(Store store);
     Optional<Store> findById(Long id);
     List<Store> findAll();
     void delete(Store store);

    // 필요하다면 여기에 추가적인 쿼리 메서드나 @Query를 정의합니다.
}