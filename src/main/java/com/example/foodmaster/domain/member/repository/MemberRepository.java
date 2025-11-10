package com.example.foodmaster.domain.member.repository;

import com.example.foodmaster.domain.member.dto.HomeDTO;
import com.example.foodmaster.domain.member.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface MemberRepository extends JpaRepository<Member, Long> {

    // 마이 페이지 화면 쿼리
    Member findMemberById(Long id);

    // 홈 화면 쿼리
    @Query("""
        select new com.example.foodmaster.domain.member.dto.HomeDTO(mem, m)
        from Member mem
        join MemberMission mm on mm.member = mem
        join Mission m on mm.mission = m
        join m.store s
        where mem.id = :memberId
        and s.detailAddress.id = mem.detailAddress.id
        and mm.isCompleted = false
""")
    Page<HomeDTO> findMemberHomePage(
            @Param("memberId") Long memberId,
            Pageable pageable
    );
}
