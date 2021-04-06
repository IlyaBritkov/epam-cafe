package com.epam.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Category extends AbstractBaseEntity {
    private String name;

    private String description;

    private final List<Product> productList = new ArrayList<>();

    public Category(Long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}
