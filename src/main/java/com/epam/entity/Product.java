package com.epam.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;
import org.joda.money.Money;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Product extends AbstractBaseEntity {
    private String name;

    @Nullable
    private String description;

    private Money price;

    //todo maybe add picture

    public Product(String name, @Nullable String description, Money price) {
        this.name = name;
        this.description = description;
        this.price = price;
    }

    public Product(Long id, String name, @Nullable String description, Money price) {
        super(id);
        this.name = name;
        this.description = description;
        this.price = price;
    }

}
