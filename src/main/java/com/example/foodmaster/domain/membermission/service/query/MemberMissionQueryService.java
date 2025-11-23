package com.example.foodmaster.domain.membermission.service.query;

import com.example.foodmaster.domain.membermission.dto.MemberMissionResDTO;

public interface MemberMissionQueryService {
    MemberMissionResDTO.MemberMissionPreviewListDTO searchMissions(
            Long memberId,
            Integer page
    );
}
