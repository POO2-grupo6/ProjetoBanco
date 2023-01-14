package main.java.view;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;

public class NaturalPersonClientView {
    public Map.Entry<String, String> getCredentials() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("digite seu CPF: ");
        String cpf = scanner.nextLine();

        System.out.print("digite sua senha: ");
        String password = scanner.nextLine();

        return new AbstractMap.SimpleEntry<>(cpf, password);
    }
}
