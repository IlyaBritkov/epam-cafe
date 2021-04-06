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
    private final UserRole userRole;

    public User(Long id, String login, String password, String firstName, String lastName, UserRole userRole) {
        super(id);
        this.login = login;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
    }
}
