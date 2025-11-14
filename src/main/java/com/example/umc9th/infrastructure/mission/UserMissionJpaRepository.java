package com.example.umc9th.infrastructure.mission;

import com.example.umc9th.application.mission.dto.MissionCardDto;
import com.example.umc9th.application.mission.dto.MyMissionRowDto;
import com.example.umc9th.application.mission.dto.ReviewTargetDto;
import com.example.umc9th.domain.mission.Mission;
import com.example.umc9th.domain.mission.UserMission;
import com.example.umc9th.domain.user.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface UserMissionJpaRepository extends JpaRepository<UserMission, Long> {

    // 이미 해당 미션 도전 중인지 체크
    boolean existsByUserAndMission(User user, Mission mission);

    // 3-1 내 미션 카드
    @org.springframework.data.jpa.repository.Query(
            value = """
    select new com.example.umc9th.application.mission.dto.MissionCardDto(
      um.id, m.id, s.name, m.title, um.status,
      case
        when um.status in (com.example.umc9th.domain.enums.UserMissionStatus.COMPLETED,
                           com.example.umc9th.domain.enums.UserMissionStatus.REVIEWED) then '성공'
        when um.status = com.example.umc9th.domain.enums.UserMissionStatus.IN_PROGRESS then '진행중'
        else '받음'
      end,
      case
        when m.endsAt is null then cast(null as int)
        else cast(function('datediff', m.endsAt, current_date) as int)
      end,
      case
        when um.status = com.example.umc9th.domain.enums.UserMissionStatus.COMPLETED
             and not exists (
               select 1 from com.example.umc9th.domain.review.Review rv
               where rv.mission.id = m.id and rv.user.id = um.user.id
             )
        then true else false
      end
    )
    from UserMission um
      join um.mission m
      join m.store s
    where um.user.id = :userId
    order by
      case
        when um.status = com.example.umc9th.domain.enums.UserMissionStatus.IN_PROGRESS then 0
        when um.status = com.example.umc9th.domain.enums.UserMissionStatus.REQUESTED   then 1
        when um.status = com.example.umc9th.domain.enums.UserMissionStatus.COMPLETED   then 2
        when um.status = com.example.umc9th.domain.enums.UserMissionStatus.REVIEWED    then 3
        else 4
      end,
      um.assignedAt desc
  """,
            countQuery = """
    select count(um)
    from UserMission um
      join um.mission m
      join m.store s
    where um.user.id = :userId
  """
    )
    Page<MissionCardDto> findMissionCards(@org.springframework.data.repository.query.Param("userId") Long userId,
                                          org.springframework.data.domain.Pageable pageable);



    // (문자열 비교 버전이 필요하면 위 쿼리에서 enum 상수 대신 'IN_PROGRESS' 등 문자열로 변경)

    // 3-2 미션 도전(REQUESTED → IN_PROGRESS)
    @Modifying(clearAutomatically = true, flushAutomatically = true)
    @Query("""
      update UserMission um
      set um.status = com.example.umc9th.domain.enums.UserMissionStatus.IN_PROGRESS,
          um.startedAt = current_timestamp,
          um.updatedAt = current_timestamp
      where um.id = :userMissionId
        and um.user.id = :userId
        and um.status = com.example.umc9th.domain.enums.UserMissionStatus.REQUESTED
    """)
    int startMission(@Param("userMissionId") Long userMissionId, @Param("userId") Long userId);

    // 3-3 리뷰 남기기 대상
    @Query("""
      select new com.example.umc9th.application.mission.dto.ReviewTargetDto(
        um.id, m.id, s.name, m.title, um.completedAt
      )
      from UserMission um
        join um.mission m
        join m.store s
        left join com.example.umc9th.domain.review.Review rv
               on rv.mission.id = m.id and rv.user.id = um.user.id
      where um.user.id = :userId
        and um.status = com.example.umc9th.domain.enums.UserMissionStatus.COMPLETED
        and rv.id is null
      order by um.completedAt desc
    """)
    Page<ReviewTargetDto> findReviewTargets(@Param("userId") Long userId, Pageable pageable);

    // 4-1 진행률(선택 지역 완료 수)
    @Query("""
      select coalesce(count(um),0)
      from UserMission um
        join um.mission m
        join m.store s
      where um.user.id = :userId
        and s.region.id = :regionId
        and um.status in (com.example.umc9th.domain.enums.UserMissionStatus.COMPLETED,
                          com.example.umc9th.domain.enums.UserMissionStatus.REVIEWED)
    """)
    long countCompletedInRegion(@Param("userId") Long userId, @Param("regionId") Long regionId);

    // 4-2 해당 지역 MY MISSION 리스트
    @org.springframework.data.jpa.repository.Query(
            value = """
    select new com.example.umc9th.application.mission.dto.MyMissionRowDto(
      um.id, m.id, m.title, s.name, um.status,
      case
        when m.endsAt is null then cast(null as integer)
        else cast(function('datediff', m.endsAt, current_date) as integer)
      end
    )
    from UserMission um
      join um.mission m
      join m.store s
    where um.user.id = :userId
      and s.region.id = :regionId
    order by
      case
        when um.status = com.example.umc9th.domain.enums.UserMissionStatus.IN_PROGRESS then 0
        when um.status = com.example.umc9th.domain.enums.UserMissionStatus.REQUESTED   then 1
        when um.status = com.example.umc9th.domain.enums.UserMissionStatus.COMPLETED   then 2
        when um.status = com.example.umc9th.domain.enums.UserMissionStatus.REVIEWED    then 3
        else 4
      end,
      case when m.endsAt is null then 1 else 0 end,
      m.endsAt asc,
      um.assignedAt desc
  """,
            countQuery = """
    select count(um)
    from UserMission um
      join um.mission m
      join m.store s
    where um.user.id = :userId
      and s.region.id = :regionId
  """
    )
    org.springframework.data.domain.Page<
            com.example.umc9th.application.mission.dto.MyMissionRowDto
            > findMyMissionsInRegion(
            @org.springframework.data.repository.query.Param("userId") Long userId,
            @org.springframework.data.repository.query.Param("regionId") Long regionId,
            org.springframework.data.domain.Pageable pageable
    );


}
