package com.epam.entity;

import com.epam.entity.enums.Star;
import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.time.OffsetDateTime;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Feedback extends AbstractBaseEntity {
    @Setter(AccessLevel.NONE)
    private final Long clientId;

    @Setter(AccessLevel.NONE)
    private final Long productId;

    @Nullable
    private String title;
    @Nullable
    private String description;

    private OffsetDateTime dateTime;
    private Star star;

    public Feedback(Long clientId, Long productId, @Nullable String title, @Nullable String description, OffsetDateTime dateTime, Star star) {
        this.clientId = clientId;
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.star = star;
    }

    public Feedback(Long id, Long clientId, Long productId, @Nullable String title, @Nullable String description, OffsetDateTime dateTime, Star star) {
        super(id);
        this.clientId = clientId;
        this.productId = productId;
        this.title = title;
        this.description = description;
        this.dateTime = dateTime;
        this.star = star;
    }
}
