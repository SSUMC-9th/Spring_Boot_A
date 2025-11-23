package com.example.foodmaster.domain.membermission.service.query;

import com.example.foodmaster.domain.member.entity.Member;
import com.example.foodmaster.domain.member.exception.MemberException;
import com.example.foodmaster.domain.member.exception.code.MemberErrorCode;
import com.example.foodmaster.domain.member.repository.MemberRepository;
import com.example.foodmaster.domain.membermission.converter.MemberMissionQueryConverter;
import com.example.foodmaster.domain.membermission.dto.MemberMissionResDTO;
import com.example.foodmaster.domain.membermission.entity.MemberMission;
import com.example.foodmaster.domain.membermission.repository.MemberMissionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService{

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Override
    @Transactional(readOnly = true)
    public MemberMissionResDTO.MemberMissionPreviewListDTO searchMissions(
            Long memberId,
            Integer page
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<MemberMission> result = memberMissionRepository.findALlByMember(member, pageRequest);

        return MemberMissionQueryConverter.toMemberMissionPreviewListDTO(result);
    }
}
