package main.java.model;

import java.util.Objects;

public class Client {
    String name;
    String cpf;
    String password;
    // birthday?
    Account checkingAccount = new CheckingAccount();
    Account savingsAccount = new SavingsAccount();
    Account paymentsAccount = new PaymentsAccount();

    public Client(String name, String cpf, String password) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    boolean passwordIsEqualTo(String string) {
        return this.password.equals(string);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return Objects.equals(cpf, client.cpf);
    }

    @Override
    public int hashCode() {
        return Objects.hash(cpf);
    }
}
