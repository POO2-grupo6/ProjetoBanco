package main.java.view;

import main.java.model.Client;
import main.java.model.NaturalPersonClient;

import java.util.Scanner;

public class NaturalPersonClientView implements ClientView {
    public Client buildANewClient() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("digite seu nome: ");
        String name = scanner.nextLine();

        System.out.print("digite seu CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("escolha uma senha: ");
        String password = scanner.nextLine();

        return new NaturalPersonClient(name, cpf, password);
    }
}
