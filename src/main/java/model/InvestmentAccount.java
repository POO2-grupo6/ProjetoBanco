package main.java.model;

import java.math.BigDecimal;

public class InvestmentAccount extends AccountThatPaysInterest {
    public InvestmentAccount(int accountNumber, BigDecimal interest) {
        super(accountNumber, interest);
    }
}
