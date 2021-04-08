package com.epam.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Getter
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
public class BankCard extends AbstractBaseEntity {
    private final Long cardNumber;

    @Setter
    private int monthExpired;

    @Setter
    private int yearExpired;

    private final int csv;

    private final String firstName;

    private final String lastName;

    private final List<Client> holderList = new ArrayList<>();

    public BankCard(Long cardNumber, int monthExpired, int yearExpired, int csv, String firstName, String lastName) {
        this.cardNumber = cardNumber;
        this.monthExpired = monthExpired;
        this.yearExpired = yearExpired;
        this.csv = csv;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public BankCard(Long id, Long cardNumber, int monthExpired, int yearExpired, int csv, String firstName, String lastName) {
        super(id);
        this.cardNumber = cardNumber;
        this.monthExpired = monthExpired;
        this.yearExpired = yearExpired;
        this.csv = csv;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public boolean addHolder(Client client) {
        return holderList.add(client);
    }

    public boolean addHolders(List<Client> clientList) {
        return holderList.addAll(clientList);
    }

    public boolean addHolders(Client... clients) {
        return holderList.addAll(Arrays.asList(clients));
    }

    public boolean removeHolder(Client client) {
        return holderList.remove(client);
    }

    public boolean removeHolders(List<Client> clientList) {
        return holderList.removeAll(clientList);
    }

    public boolean removeHolders(Client... clients) {
        return holderList.removeAll(Arrays.asList(clients));
    }

}
