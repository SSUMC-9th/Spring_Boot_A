package com.example.umc9th.domain.mission.entity.mapping;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QUserMission is a Querydsl query type for UserMission
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QUserMission extends EntityPathBase<UserMission> {

    private static final long serialVersionUID = -1061295321L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QUserMission userMission = new QUserMission("userMission");

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.example.umc9th.domain.member.entity.QMember member;

    public final com.example.umc9th.domain.mission.entity.QMission mission;

    public final StringPath status = createString("status");

    public QUserMission(String variable) {
        this(UserMission.class, forVariable(variable), INITS);
    }

    public QUserMission(Path<? extends UserMission> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QUserMission(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QUserMission(PathMetadata metadata, PathInits inits) {
        this(UserMission.class, metadata, inits);
    }

    public QUserMission(Class<? extends UserMission> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.example.umc9th.domain.member.entity.QMember(forProperty("member")) : null;
        this.mission = inits.isInitialized("mission") ? new com.example.umc9th.domain.mission.entity.QMission(forProperty("mission"), inits.get("mission")) : null;
    }

}

