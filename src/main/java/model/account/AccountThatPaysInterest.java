package main.java.model.account;

import java.math.BigDecimal;

public abstract class AccountThatPaysInterest extends Account {
    BigDecimal interest;

    protected AccountThatPaysInterest(int accountNumber, BigDecimal interest) {
        super(accountNumber);
        this.interest = interest;
    }

    public BigDecimal getInterest() {
        return this.interest;
    }
}
