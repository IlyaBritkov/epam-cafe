package com.epam.entity;

import com.epam.enums.Star;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Feedback extends AbstractBaseEntity {
    private final Client client;
    private final Product product;
    private String title;
    private String description;
    private LocalDate date;
    private Star star;

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
