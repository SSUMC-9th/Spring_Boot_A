package com.example.umc9th.application.mission.service;

import com.example.umc9th.application.mission.dto.MissionResDTO;
import com.example.umc9th.domain.mission.Mission;
import com.example.umc9th.domain.mission.converter.MissionConverter;
import com.example.umc9th.global.paging.PageResponse;
import com.example.umc9th.infrastructure.mission.MissionJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionQueryService {

    private final MissionJpaRepository missionRepository;

    public PageResponse<MissionResDTO.StoreMission> getStoreMissions(Long storeId, int page) {

        Pageable pageable = PageRequest.of(page, 10);

        Page<Mission> missions = missionRepository.findByStoreId(storeId, pageable);

        Page<MissionResDTO.StoreMission> dtoPage =
                missions.map(MissionConverter::toStoreMission);

        return PageResponse.from(dtoPage);
    }
}

