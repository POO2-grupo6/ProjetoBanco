package main.java.model.client;

import main.java.model.account.Account;

public class NaturalPersonClient extends Client {
    Account savingsAccount;

    public NaturalPersonClient(String name, String registrationId, String password) {
        super(name, registrationId, password);
    }

    public Account getSavingsAccount() {
        return savingsAccount;
    }

    public void setSavingsAccount(Account savingsAccount) {
        this.savingsAccount = savingsAccount;
    }
}
