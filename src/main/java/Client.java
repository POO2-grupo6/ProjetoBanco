package main.java;

public class Client {
    String name;
    String cpf;
    String password;
    Account checkingAccount = new CheckingAccount();
    Account savingsAccount = new SavingsAccount();
    Account paymentsAccount = new PaymentsAccount();
}
