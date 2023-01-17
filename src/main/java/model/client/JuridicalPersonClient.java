package main.java.model.client;

import main.java.model.account.Account;

import java.math.BigDecimal;

public class JuridicalPersonClient extends Client {
    private static final BigDecimal WITHDRAWAL_FEE = BigDecimal.valueOf(0.005);

    public JuridicalPersonClient(String name, String registrationId, String password) {
        super(name, registrationId, password);
    }

    @Override
    public void withdraw(Account account, BigDecimal value) {
        super.withdraw(account, value.multiply(BigDecimal.ONE.add(WITHDRAWAL_FEE)));
    }

    @Override
    public void transfer(Account sourceAccount, Account destinationAccount, BigDecimal value) {
        super.transfer(sourceAccount, destinationAccount, value);
        this.getCheckingAccount().removeFromBalance(value.multiply(BigDecimal.ONE.add(WITHDRAWAL_FEE)));
    }
}
