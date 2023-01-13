package main.java.model;

import java.math.BigDecimal;

public abstract class Account {
    String accountNumber;
    BigDecimal balance = BigDecimal.ZERO;

    public BigDecimal getBalance() {
        return balance;
    }
}
