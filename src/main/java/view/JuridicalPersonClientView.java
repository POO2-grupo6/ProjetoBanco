package main.java.view;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Scanner;

public class JuridicalPersonClientView {
    public Map.Entry<String, String> getCredentials() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("digite o CNPJ: ");
        String cnpj = scanner.nextLine();

        System.out.print("digite a senha: ");
        String password = scanner.nextLine();

        return new AbstractMap.SimpleEntry<>(cnpj, password);
    }
}
