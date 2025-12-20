package com.example.umc9th.domain.member.service.command;


import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.auth.enums.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;




@Service
@RequiredArgsConstructor
public class MemberCommandServiceImpl implements MemberCommandService{

    private final MemberRepository memberRepository;
    // Password Encoder
    private final PasswordEncoder passwordEncoder;

    // 회원가입
    @Override
    @Transactional
    public MemberResDTO.JoinDTO signup(
            MemberReqDTO.JoinDTO dto
    ){

// 1. 비밀번호 암호화 (Salted)
        String encodedPassword = passwordEncoder.encode(dto.password());

        // 2. DTO -> Entity 변환
        Member member = MemberConverter.toMember(dto, encodedPassword, Role.ROLE_USER);

        // 3. DB에 저장 (이 단계를 거쳐야 ID와 생성일자가 생깁니다)
        Member savedMember = memberRepository.save(member);

        // 4. Entity -> DTO 변환 및 반환
        return MemberConverter.toJoinDTO(savedMember);
    }
}