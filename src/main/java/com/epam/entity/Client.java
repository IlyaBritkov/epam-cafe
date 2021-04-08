package com.epam.entity;

import com.epam.enums.ClientStatus;
import com.epam.enums.UserRole;
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
public class Client extends User {
    @Setter
    private int loyaltyPoints;

    @Setter
    private ClientStatus clientStatus;

    private final List<BankCard> bankCardList = new ArrayList<>();

    private final List<Product> favoriteProductList = new ArrayList<>();

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

    public boolean addBankCard(BankCard bankCard) {
        return bankCardList.add(bankCard);
    }

    public boolean addBankCards(List<BankCard> bankCards) {
        return bankCardList.addAll(bankCards);
    }

    public boolean addBankCards(BankCard... bankCards) {
        return bankCardList.addAll(Arrays.asList(bankCards));
    }

    public boolean removeBankCard(BankCard bankCard) {
        return bankCardList.remove(bankCard);
    }

    public boolean removeBankCard(List<BankCard> bankCards) {
        return bankCardList.removeAll(bankCards);
    }

    public boolean removeBankCard(BankCard... bankCards) {
        return bankCardList.removeAll(Arrays.asList(bankCards));
    }
}
