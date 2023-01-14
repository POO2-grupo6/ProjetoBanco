package main.java.view;

import main.java.model.Client;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;

public interface ClientView {
    static Map.Entry<String, String> getCredentials() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("digite o CPF ou CNPJ: ");
        String registrationId = scanner.nextLine();

        System.out.print("digite a senha: ");
        String password = scanner.nextLine();

        return new AbstractMap.SimpleEntry<>(registrationId, password);
    }

    Client buildANewClient();
}
