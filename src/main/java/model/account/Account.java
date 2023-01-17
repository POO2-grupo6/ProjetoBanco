package main.java.model.account;

import main.java.model.bank.InsufficientFundsException;

import java.math.BigDecimal;

public abstract class Account {
    private final int accountNumber;

    private BigDecimal balance = BigDecimal.ZERO;

    protected Account(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void addToBalance(BigDecimal value) {
        this.balance = this.balance.add(value);
    }

    public void removeFromBalance(BigDecimal value) {
        if (value.compareTo(this.balance) > 0) {
            throw new InsufficientFundsException();
        }

        this.balance = this.balance.subtract(value);
    }
}
