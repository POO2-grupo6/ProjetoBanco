package main.java.model;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Client {
    String name;
    String registrationId;
    private String password; // encrypted?
    // birthday?
    CheckingAccount checkingAccount;
    InvestmentAccount investmentAccount;

    protected Client(String name, String registrationId, String password) {
        this.name = name;
        this.registrationId = registrationId;
        this.password = password;
    }

    boolean deposit(Account account, BigDecimal value) {
        if (!(account instanceof DepositAccount)) {
            throw new DestinationAccountNotADepositAccountException();
        }

        if (account instanceof AccountThatPaysInterest) {
            AccountThatPaysInterest accountThatPaysInterest = (AccountThatPaysInterest) account;
            BigDecimal valueWithInterest = value.multiply(accountThatPaysInterest.getInterest());
            accountThatPaysInterest.addToBalance(valueWithInterest);
        } else {
            account.addToBalance(value);
        }

        return true;
    }

    public String getName() {
        return name;
    }

    boolean passwordIsEqualTo(String string) {
        return this.password.equals(string);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(registrationId, client.registrationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(registrationId);
    }
}
