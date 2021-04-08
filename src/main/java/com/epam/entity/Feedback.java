package com.epam.entity;

import com.epam.enums.Star;
import lombok.*;
import org.jetbrains.annotations.Nullable;

import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Feedback extends AbstractBaseEntity {
    @Setter(AccessLevel.NONE)
    private final Client client;

    @Setter(AccessLevel.NONE)
    private final Product product;

    @Nullable
    private String title;
    @Nullable
    private String description;

    private LocalDate date;
    private Star star;

    public Feedback(Client client, Product product, @Nullable String title, @Nullable String description, LocalDate date, Star star) {
        this.client = client;
        this.product = product;
        this.title = title;
        this.description = description;
        this.date = date;
        this.star = star;
    }

    public Feedback(Long id, Client client, Product product, String title, String description, LocalDate date, Star star) {
        super(id);
        this.client = client;
        this.product = product;
        this.title = title;
        this.description = description;
        this.date = date;
        this.star = star;
    }
}
