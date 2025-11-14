package com.example.umc9th.infrastructure.mission;

import com.example.umc9th.domain.mission.Mission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MissionJpaRepository extends JpaRepository<Mission, Long> {
}
