package com.example.foodmaster.domain.member.converter;

import com.example.foodmaster.domain.member.dto.MemberReqDTO;
import com.example.foodmaster.domain.member.dto.MemberResDTO;
import com.example.foodmaster.domain.member.entity.Member;
import lombok.Builder;

@Builder
public class MemberConverter {

    // Entity -> DTO
    public static MemberResDTO.JoinDTO toJoinDTO(
            Member member
    ) {
        return MemberResDTO.JoinDTO.builder()
                .memberId(member.getId())
                .createAt(member.getCreatedAt())
                .build();
    }

    // DTO -> Entity
    public static Member toMember(
            MemberReqDTO.JoinDTO dto
    ) {
        return Member.builder()
                .name(dto.name())
                .birth(dto.birth())
                .detailAddress(dto.detailAddress())
                .gender(dto.gender())
                .build();
    }
}
