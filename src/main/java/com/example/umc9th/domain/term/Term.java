package com.example.umc9th.domain.term;

import com.example.umc9th.domain.common.BaseTimeEntity;
import com.example.umc9th.domain.enums.TermType;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "terms",
        uniqueConstraints = @UniqueConstraint(name = "uq_terms_type_version", columnNames = {"type","version"}))
@Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor @Builder
public class Term extends BaseTimeEntity {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "term_id")
    private Long id;

    @Column(length = 100, nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "ENUM('SERVICE','PRIVACY','LOCATION','MARKETING')")
    private TermType type;

    @Column(length = 20, nullable = false)
    private String version;

    @Column(name = "is_required", nullable = false)
    private Boolean required = true;

    @Lob
    private String content;

    @OneToMany(mappedBy = "term", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<UserTerm> userTerms = new ArrayList<>();
}

