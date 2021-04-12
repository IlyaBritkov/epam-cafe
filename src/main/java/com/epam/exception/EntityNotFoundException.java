package com.epam.exception;

import java.util.Arrays;

public class EntityNotFoundException extends RuntimeException {
    private final Class<?> clazz;

    private final Object[] args;

    public EntityNotFoundException(Class<?> clazz) {
        this.clazz = clazz;
        this.args = null;
    }

    public EntityNotFoundException(Class<?> clazz, Object... args) {
        this.clazz = clazz;
        this.args = args;
    }

    @Override
    public String getMessage() {
        if (args == null) {
            return String.format("Cannot find %s\n", clazz.getSimpleName());
        } else {
            return String.format("Cannot find %s with values: %s\n", clazz.getSimpleName(), Arrays.toString(args));
        }
    }

}
