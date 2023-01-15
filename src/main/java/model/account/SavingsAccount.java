package main.java.model.account;

import java.math.BigDecimal;

public class SavingsAccount extends AccountThatPaysInterest implements DepositAccount {
    static final BigDecimal SAVINGS_ACCOUNT_INTEREST = BigDecimal.valueOf(0.01);


    public SavingsAccount(int accountNumber) {
        super(accountNumber, SAVINGS_ACCOUNT_INTEREST);
    }
}
