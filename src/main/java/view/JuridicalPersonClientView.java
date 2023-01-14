package main.java.view;

import main.java.model.Client;
import main.java.model.JuridicalPersonClient;

import java.util.Scanner;

public class JuridicalPersonClientView implements ClientView {
    public Client buildANewClient() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("digite o nome: ");
        String name = scanner.nextLine();

        System.out.print("digite o CNPJ: ");
        String cnpj = scanner.nextLine();

        System.out.print("escolha uma senha: ");
        String password = scanner.nextLine();

        return new JuridicalPersonClient(name, cnpj, password);
    }
}
