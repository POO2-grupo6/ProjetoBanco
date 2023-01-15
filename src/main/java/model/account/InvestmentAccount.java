package main.java.model.account;

import java.math.BigDecimal;

public class InvestmentAccount extends AccountThatPaysInterest {
    public static final BigDecimal INTEREST_FOR_NATURAL_PERSONS = BigDecimal.valueOf(0.015);
    public static final BigDecimal INTEREST_FOR_JURIDICAL_PERSONS = BigDecimal.valueOf(0.035);

    public InvestmentAccount(int accountNumber, BigDecimal interest) {
        super(accountNumber, interest);
    }
}
