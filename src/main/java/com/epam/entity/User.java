package com.epam.entity;

import com.epam.enums.UserRole;
import lombok.*;

@Getter
@Setter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class User extends AbstractBaseEntity {
    private String login;

    private String password;

    private String firstName;

    private String lastName;

    @Setter(AccessLevel.NONE)
    private final UserRole role;

    public User(String login, String password, String firstName, String lastName, UserRole role) {
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }

    public User(Long id, String login, String password, String firstName, String lastName, UserRole role) {
        super(id);
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.role = role;
    }
}
