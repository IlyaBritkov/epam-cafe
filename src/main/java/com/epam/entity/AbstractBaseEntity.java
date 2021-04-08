package com.epam.entity;

import lombok.*;
import org.jetbrains.annotations.Nullable;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@ToString
public abstract class AbstractBaseEntity {
    @Nullable
    @EqualsAndHashCode.Include
    private Long id;

}
