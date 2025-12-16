package com.example.foodmaster.domain.member.converter;

import com.example.foodmaster.domain.member.dto.join.MemberReqDTO;
import com.example.foodmaster.domain.member.dto.join.MemberResDTO;
import com.example.foodmaster.domain.member.entity.Member;
import com.example.foodmaster.global.auth.Role;
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
            MemberReqDTO.JoinDTO dto,
            String password,
            Role role
    ) {
        return Member.builder()
                // DTO에서 가져온 필드
                .name(dto.name())
                .email(dto.email())
                .password(password)
                .birth(dto.birth())
                .gender(dto.gender())
                .nickName(dto.nickName())
                .loginType(dto.loginType())
                .phoneNumber(dto.phoneNumber())

                // Converter에서 기본값을 설정하는 필드
                .role(role) // Role.ROLE_USER
                .point(0)   // int의 기본값
                .build();
    }
}
