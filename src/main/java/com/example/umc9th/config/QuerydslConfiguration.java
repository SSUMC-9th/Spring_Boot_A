package com.example.umc9th.config; // 패키지 경로는 프로젝트에 맞게 수정

import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QuerydslConfiguration {

    // 현재 JPA 엔티티 매니저를 주입받습니다.
    @PersistenceContext
    private EntityManager entityManager;

    /**
     * JPAQueryFactory를 스프링 빈으로 등록
     * 이 빈은 EntityManager를 주입받아 QueryDSL 쿼리를 생성하는 데 사용됩니다.
     */
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        // 주입받은 EntityManager를 사용하여 팩토리를 생성합니다.
        return new JPAQueryFactory(entityManager);
    }
}