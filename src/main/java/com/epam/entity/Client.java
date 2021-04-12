package com.epam.entity;

import com.epam.entity.enums.ClientStatus;
import com.epam.entity.enums.UserRole;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class Client extends User {
    @Setter
    private int loyaltyPoints;

    @Setter
    private ClientStatus clientStatus;

    public Client(String login, String password, String firstName, String lastName, UserRole role) {
        super(login, password, firstName, lastName, role);
        this.loyaltyPoints = 0;
        this.clientStatus = ClientStatus.NORMAL;
    }

    public Client(String login, String password, String firstName, String lastName, UserRole role, int loyaltyPoints) {
        super(login, password, firstName, lastName, role);
        this.loyaltyPoints = loyaltyPoints;
        this.clientStatus = ClientStatus.NORMAL;
    }

    public Client(Long id, String login, String password, String firstName, String lastName, UserRole role, int loyaltyPoints, ClientStatus clientStatus) {
        super(id, login, password, firstName, lastName, role);
        this.loyaltyPoints = loyaltyPoints;
        this.clientStatus = clientStatus;
    }

}
