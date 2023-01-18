package main.java;

import main.java.model.bank.Bank;
import main.java.model.client.Person;
import main.java.view.BankView;

public class Aplicacao {
    public static void main(String[] args) {
        Bank bank = new Bank(); // dar nome ao banco?
        bank.run(new Person(), new BankView());
    }
}
