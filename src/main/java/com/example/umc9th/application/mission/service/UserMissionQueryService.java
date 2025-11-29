package com.example.umc9th.application.mission.service;

import com.example.umc9th.application.mission.dto.UserMissionResDTO;
import com.example.umc9th.domain.mission.UserMission;
import com.example.umc9th.global.paging.PageResponse;
import com.example.umc9th.infrastructure.mission.UserMissionJpaRepository;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import com.example.umc9th.domain.mission.converter.UserMissionConverter;
import com.example.umc9th.domain.enums.UserMissionStatus;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserMissionQueryService {

    private final UserMissionJpaRepository userMissionRepository;

    public PageResponse<UserMissionResDTO.Challenge> getMyProgressMissions(Long userId, int page) {
        Pageable pageable = PageRequest.of(page, 10);

        Page<UserMission> pageEntity =
                userMissionRepository.findByUserIdAndStatus(userId, UserMissionStatus.IN_PROGRESS, pageable);

        Page<UserMissionResDTO.Challenge> dtoPage =
                pageEntity.map(UserMissionConverter::toChallengeDTO);

        return PageResponse.from(dtoPage);
    }
}

