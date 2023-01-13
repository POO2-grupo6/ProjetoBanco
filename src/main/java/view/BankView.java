package main.java.view;

import main.java.model.Client;

public class BankView implements Viewable{

    @Override
    public void showGreetingsMessage() {
        System.out.println("Ola! Seja bem-vindo ao Banco!");
    }

    @Override
    public void showStartMenu() {
        System.out.println("escolha uma opção:");
        System.out.println("1 - abrir nova conta");
        System.out.println("2 - efetuar login");
        System.out.println("3 - sair");
    }

    @Override
    public void showLoggedMenu(Client client) {
        System.out.println("Olá " + client.getName() + ", seu saldo é de ");
        System.out.println("escolha uma opção:");
        System.out.println("1 - depositar");
        System.out.println("2 - sacar");
        System.out.println("3 - transferir");
        System.out.println("4 - deslogar");
    }

    @Override
    public void showFarewellMessage() {

    }

    @Override
    public int getOptionFromUser() {
        return 0;
    }
}
