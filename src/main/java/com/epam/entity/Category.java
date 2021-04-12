package com.epam.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.jetbrains.annotations.Nullable;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Category extends AbstractBaseEntity {
    private String name;

    @Nullable
    private String description;

    public Category(String name, @Nullable String description) {
        this.name = name;
        this.description = description;
    }

    public Category(Long id, String name, @Nullable String description) {
        super(id);
        this.name = name;
        this.description = description;
    }
}
