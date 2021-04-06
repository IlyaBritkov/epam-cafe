package com.epam.entity;

import lombok.*;

@Getter
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public abstract class AbstractBaseEntity {
    @NonNull
    @EqualsAndHashCode.Include
    private final Long id;

}
