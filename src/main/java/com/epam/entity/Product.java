package com.epam.entity;

import lombok.*;
import org.jetbrains.annotations.Nullable;
import org.joda.money.CurrencyUnit;
import org.joda.money.Money;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Product extends AbstractBaseEntity {
    private String name;

    @Nullable
    private String description;

    @Setter(AccessLevel.NONE)
    private static CurrencyUnit currencyUnit = CurrencyUnit.of("BYN");

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

    public static CurrencyUnit getCurrencyUnit() {
        return currencyUnit;
    }
}
