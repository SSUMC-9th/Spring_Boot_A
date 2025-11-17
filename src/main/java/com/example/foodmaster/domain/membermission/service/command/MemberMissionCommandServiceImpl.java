package com.example.foodmaster.domain.membermission.service.command;

import com.example.foodmaster.domain.member.entity.Member;
import com.example.foodmaster.domain.member.exception.MemberException;
import com.example.foodmaster.domain.member.exception.code.MemberErrorCode;
import com.example.foodmaster.domain.member.repository.MemberRepository;
import com.example.foodmaster.domain.membermission.converter.MemberMissionConverter;
import com.example.foodmaster.domain.membermission.dto.MemberMissionReqDTO;
import com.example.foodmaster.domain.membermission.dto.MemberMissionResDTO;
import com.example.foodmaster.domain.membermission.entity.MemberMission;
import com.example.foodmaster.domain.membermission.exception.MemberMissionException;
import com.example.foodmaster.domain.membermission.exception.code.MemberMissionErrorCode;
import com.example.foodmaster.domain.membermission.repository.MemberMissionRepository;
import com.example.foodmaster.domain.mission.entity.Mission;
import com.example.foodmaster.domain.mission.exception.MissionException;
import com.example.foodmaster.domain.mission.exception.code.MissionErrorCode;
import com.example.foodmaster.domain.mission.repository.MissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService{

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional
    public MemberMissionResDTO.MemberMissionDTO startMission(MemberMissionReqDTO.MemberMissionDTO dto) {

        // dto의 id 정보를 이용해 엔티티 객체 조회
        Member member = memberRepository.findById(dto.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Mission mission = missionRepository.findById(dto.missionId())
                .orElseThrow(() -> new MissionException(MissionErrorCode.NOT_FOUND));


        // 이미 진행중인 미션인지 검증

        Boolean alreadyExists = memberMissionRepository.existsByMemberAndMission(member, mission);

        if (alreadyExists) {
            throw new MemberMissionException(MemberMissionErrorCode.ALREADY_EXISTS);
        }

        // converter를 이용해 MemberMission 엔티티 생성
        MemberMission newMission = MemberMissionConverter.toMemberMission(member, mission);

        // Repository를 이용해 DB에 저장하고 결과 반환
        MemberMission savedMission = memberMissionRepository.save(newMission);

        return new MemberMissionResDTO.MemberMissionDTO(savedMission.getId());
    }

}
