package com.example.umc9th.domain.term;

import jakarta.persistence.Embeddable;
import lombok.*;

import java.io.Serializable;

@Embeddable
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor @EqualsAndHashCode
public class UserTermId implements Serializable {
    private Long userId;
    private Long termId;
}

