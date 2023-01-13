package main.java.model;

public class Client {
    String name;
    String cpf;
    String password;
    Account checkingAccount = new CheckingAccount();
    Account savingsAccount = new SavingsAccount();
    Account paymentsAccount = new PaymentsAccount();

    public String getName() {
        return name;
    }
}
