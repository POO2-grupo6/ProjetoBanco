package main.java.view;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;

public class NaturalPersonClientView {
    public Map.Entry<String, String> getCredentials() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("digite o CPF ou CNPJ: ");
        String registrationId = scanner.nextLine();

        System.out.print("digite a senha: ");
        String password = scanner.nextLine();

        return new AbstractMap.SimpleEntry<>(registrationId, password);
    }
}
