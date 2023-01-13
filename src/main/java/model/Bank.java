package main.java.model;

import main.java.view.BankView;

import java.util.HashSet;
import java.util.Set;

public class Bank { // talvez criar BankController
    Set<Client> clients = new HashSet<>();

    public void run(BankView bankView) {

        bankView.showGreetingsMessage();

        bankView.showStartMenu();

        bankView.getOptionFromUser();



    }
}
