package main.java.model;

import java.math.BigDecimal;

public abstract class Account {
    int accountNumber;
    BigDecimal balance = BigDecimal.ZERO;

    protected Account(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public boolean addToBalance(BigDecimal value) {
        this.balance = this.balance.add(value);

        return true;
    }
}
