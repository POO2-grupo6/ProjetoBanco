package main.java.view;

import java.util.Scanner;

public class BankView { //singleton?
    public void showGreetingsMessage() {
        System.out.println("Olá! Seja bem-vindo ao Banco!");
    }

    public void showStartMenu() {
        System.out.println();
        System.out.println("escolha uma opção:");
        System.out.println("1 - cadastrar novo cliente pessoa física");
        System.out.println("2 - cadastrar novo cliente pessoa jurídica");
        System.out.println("3 - efetuar login");
        System.out.println("4 - sair");
    }

    public void showLoggedMenu(String name) {
        System.out.println();
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
