package com.example.foodmaster.global.auth;

import com.example.foodmaster.domain.member.entity.Member;
import com.example.foodmaster.domain.member.exception.MemberException;
import com.example.foodmaster.domain.member.exception.code.MemberErrorCode;
import com.example.foodmaster.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // 검증할 멤버 조회
        Member member = memberRepository.findByEmail(username).orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        // CustomeUserDetails 반환
        return new CustomUserDetails(member);
    }
}
