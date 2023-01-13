package main.java.view;

import main.java.model.Client;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;

public class BankView implements Viewable{ //singleton?

    @Override
    public void showGreetingsMessage() {
        System.out.println("Ola! Seja bem-vindo ao Banco!");
    }

    @Override
    public void showStartMenu() {
        System.out.println("escolha uma opção:");
        System.out.println("1 - cadastrar novo cliente");
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
        System.out.println("Até logo!");
    }

    @Override
    public int getOptionFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public Map.Entry<String, String> getCredentials() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("digite seu CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("digite sua senha: ");
        String password = scanner.nextLine();

        return new AbstractMap.SimpleEntry<>(cpf, password);
    }
}
