package com.example.umc9th.domain.term;

import com.example.umc9th.domain.user.User;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "user_terms")
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class UserTerm {

    @EmbeddedId
    private UserTermId id;

    @MapsId("userId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "user_id",
            foreignKey = @ForeignKey(name = "fk_user_terms_user"))
    private User user;

    @MapsId("termId")
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "term_id",
            foreignKey = @ForeignKey(name = "fk_user_terms_term"))
    private Term term;

    @Column(nullable = false)
    private Boolean agreed = true;

    @Column(nullable = false)
    private LocalDateTime agreedAt;

    @Column(length = 64)
    private String agreedIp;
}

