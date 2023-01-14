package main.java.view;

import java.util.Scanner;

public class BankView { //singleton?
    public void showGreetingsMessage() {
        System.out.println("Ola! Seja bem-vindo ao Banco!");
    }

    public void showStartMenu() {
        System.out.println("escolha uma opção:");
        System.out.println("1 - cadastrar novo cliente");
        System.out.println("2 - efetuar login");
        System.out.println("3 - sair");
    }

    public void showLoggedMenu(String name) {
        System.out.println("Olá, " + name + "!");
        System.out.println("escolha uma opção:");
        System.out.println("1 - abrir conta");
        System.out.println("2 - sacar");
        System.out.println("3 - depositar");
        System.out.println("4 - transferir");
        System.out.println("5 - investir");
        System.out.println("6 - consultar saldo");
        System.out.println("7 - deslogar");
    }

    public void showFarewellMessage() {
        System.out.println("Até logo!");
    }

    public int getOptionFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }
}
