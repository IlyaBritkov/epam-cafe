package com.epam.entity.enums;

import com.epam.exception.EntityNotFoundException;

public enum Star {
    ONE,
    TWO,
    THREE,
    FOUR,
    FIVE;

    public static Star resolveStarByName(String name) {
        try {
            name = name.toUpperCase();
            return Star.valueOf(name);
        } catch (Exception e) {
            throw new EntityNotFoundException(Star.class, name);
        }
    }

}
