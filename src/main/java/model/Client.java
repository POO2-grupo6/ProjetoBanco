package main.java.model;

import java.math.BigDecimal;

public abstract class Client {
    String name;
    private String password; // encrypted?
    // birthday?
    CheckingAccount checkingAccount;
    InvestmentAccount investmentAccount;

    protected Client(String name, String password) {
        this.name = name;
        this.password = password;
    }

    boolean deposit(Account account, BigDecimal value) {
        if (!(account instanceof DepositAccount)) {
            // throw exception
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
}
