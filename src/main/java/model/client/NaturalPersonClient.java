package main.java.model.client;

import main.java.model.account.Account;
import main.java.model.account.SavingsAccount;
import main.java.model.bank.AccountAlreadyExistsException;

import java.math.BigDecimal;

public class NaturalPersonClient extends Client {
    private Account savingsAccount;

    public NaturalPersonClient(String name, String registrationId, String password) {
        super(name, registrationId, password);
    }

    public Account getSavingsAccount() {
        return savingsAccount;
    }

    private void setSavingsAccount(Account savingsAccount) {
        this.savingsAccount = savingsAccount;
    }

    @Override
    public BigDecimal getTotalBalance() {
        BigDecimal savingsBalance = this.savingsAccount == null ? BigDecimal.ZERO : this.savingsAccount.getBalance();
        return super.getTotalBalance().add(savingsBalance);
    }

    public void openSavingsAccount(int accountNumber) {
        if (this.getSavingsAccount() != null) {
            throw new AccountAlreadyExistsException();
        }

        this.setSavingsAccount(new SavingsAccount(accountNumber));
    }
}
