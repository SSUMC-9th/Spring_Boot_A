package com.example.umc9th.domain.member.service.query;

import com.example.umc9th.domain.member.converter.MemberConverter;
import com.example.umc9th.domain.member.dto.MemberReqDTO;
import com.example.umc9th.domain.member.dto.MemberResDTO;
import com.example.umc9th.domain.member.entity.Member;
import com.example.umc9th.domain.member.exception.MemberException;
import com.example.umc9th.domain.member.exception.code.MemberErrorCode;
import com.example.umc9th.domain.member.repository.MemberRepository;
import com.example.umc9th.global.auth.entity.CustomUserDetails;
import com.example.umc9th.global.auth.jwt.JwtUtil;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.stereotype.Service;

//@Service
//@RequiredArgsConstructor
//public class MemberQueryServiceImpl implements MemberQueryService{
//
//    private final MemberRepository memberRepository;
//    private final JwtUtil jwtUtil;
//    private final PasswordEncoder encoder;
//
//    @Override
//    public MemberResDTO.LoginDTO login(
//            MemberReqDTO.@Valid LoginDTO dto
//    ) {
//
//        // Member 조회
//        Member member = memberRepository.findByEmail(dto.email())
//                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
//
//        // 비밀번호 검증
//        if (!encoder.matches(dto.password(), member.getPassword())){
//            throw new MemberException(MemberErrorCode.INVALID);
//        }
//
//        // JWT 토큰 발급용 UserDetails
//        CustomUserDetails userDetails = new CustomUserDetails(member);
//
//        // 엑세스 토큰 발급
//        String accessToken = jwtUtil.createAccessToken(userDetails);
//
//        // DTO 조립
//        return MemberConverter.toLoginDTO(member, accessToken);
//    }
// }

//세션 방식 로그인 로그아웃
@Service
@RequiredArgsConstructor
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder encoder;
    private final HttpServletRequest request; // 세션을 관리하기 위해 주입

    @Override
    @Transactional
    public MemberResDTO.LoginDTO login(MemberReqDTO.LoginDTO dto) {

        // 1. 유저 검증
        Member member = memberRepository.findByEmail(dto.email())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));
        // 2. 비밀번호 검증
        if (!encoder.matches(dto.password(), member.getPassword())) {
            throw new MemberException(MemberErrorCode.INVALID);
        }

        // 3. Spring Security 인증 객체 생성 및 컨텍스트 홀더에 등록
        CustomUserDetails userDetails = new CustomUserDetails(member);
        UsernamePasswordAuthenticationToken authentication =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

        // 이 부분이 있어야 이후 요청에서 @AuthenticationPrincipal 등으로 유저를 가져올 수 있습니다.
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // 4. 세션에 Security Context 저장
        HttpSession session = request.getSession(true);
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, SecurityContextHolder.getContext());

        return MemberConverter.toLoginDTO(member);

 }


    @Override
    public void logout(HttpServletRequest request, HttpServletResponse response) {
        // 1. Spring Security 인증 정보 삭제
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null) {
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }

        // 2. 세션 수동 무효화 (가장 확실한 방법)
        HttpSession session = request.getSession(false); // 세션이 존재할 때만 가져옴
        if (session != null) {
            session.invalidate(); // 서버에서 세션 파기
        }

        // 3. 브라우저의 쿠키 강제 만료 (JSESSIONID 제거)
        Cookie cookie = new Cookie("JSESSIONID", null);
        cookie.setPath("/"); // 생성 시 설정된 경로와 일치해야 함
        cookie.setMaxAge(0);  // 유효시간을 0으로 만들어 삭제
        response.addCookie(cookie);
    }


}




