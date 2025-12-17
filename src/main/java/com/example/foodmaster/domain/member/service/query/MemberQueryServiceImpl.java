package com.example.foodmaster.domain.member.service.query;

import com.example.foodmaster.domain.member.converter.MemberConverter;
import com.example.foodmaster.domain.member.dto.join.MemberReqDTO;
import com.example.foodmaster.domain.member.dto.join.MemberResDTO;
import com.example.foodmaster.domain.member.entity.Member;
import com.example.foodmaster.domain.member.exception.MemberException;
import com.example.foodmaster.domain.member.exception.code.MemberErrorCode;
import com.example.foodmaster.domain.member.repository.MemberRepository;
import com.example.foodmaster.global.auth.CustomUserDetails;
import com.example.foodmaster.global.auth.JwtUtil;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final JwtUtil jwtUtil;
    private final PasswordEncoder encoder;

    @Override
    public MemberResDTO.LoginDTO login(
            MemberReqDTO.@Valid LoginDTO dto) {
        // member 조회
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        // 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())){
            throw new MemberException(MemberErrorCode.NOT_FOUND);
        }

        // JWT 토큰 발급용 UserDetails
        CustomUserDetails userDetails = new CustomUserDetails(member);

        // 엑세스 토큰 발급
        String accessToken = jwtUtil.createAccessToken(userDetails);

        // DTO 조립
        return MemberConverter.toLoginDTO(member, accessToken);
    }
}
