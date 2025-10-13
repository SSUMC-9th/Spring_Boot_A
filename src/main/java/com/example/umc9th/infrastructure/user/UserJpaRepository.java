package com.example.umc9th.infrastructure.user;

import com.example.umc9th.application.user.dto.UserSummaryDto;
import com.example.umc9th.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserJpaRepository extends JpaRepository<User, Long> {

    @Query("""
      select new com.example.umc9th.application.user.dto.UserSummaryDto(
        u.id, u.nickname, u.email, u.phone,
        case when u.phone is null or u.phone = '' then false else true end,
        (select count(r) from Review r where r.user.id = :userId)
      )
      from User u
      where u.id = :userId
    """)
    UserSummaryDto findSummary(Long userId);
}
