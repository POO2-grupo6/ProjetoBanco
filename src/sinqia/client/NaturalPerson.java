package sinqia.client;

import sinqia.account.Account;

public class NaturalPerson extends Client {
    private String cpf;
    private Account[] accounts = new Account[3];

    public NaturalPerson(String name, String cpf) {
        super(name);
        this.cpf = cpf;
    }

    @Override
    public Account[] getAccounts() {
        return accounts;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
