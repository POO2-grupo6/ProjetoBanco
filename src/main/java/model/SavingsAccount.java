package main.java.model;

import java.math.BigDecimal;

public class SavingsAccount extends AccountThatPaysInterest implements DepositAccount {
    static final BigDecimal SAVINGS_ACCOUNT_INTEREST = BigDecimal.valueOf(0.01);


    protected SavingsAccount(int accountNumber) {
        super(accountNumber, SAVINGS_ACCOUNT_INTEREST);
    }
}
