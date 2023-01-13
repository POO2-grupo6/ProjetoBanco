package main.java.model;

import main.java.view.BankView;
import main.java.view.ClientView;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bank { // talvez criar BankController
    Set<Client> clients = new HashSet<>();

    public void run(BankView bankView) {

        bankView.showGreetingsMessage();

        while (true) {
            bankView.showStartMenu();

            int option = bankView.getOptionFromUser();

            switch (option) {
                case 1:     // fazer enum? cadastrar novo cliente
                    boolean successfullyRegistered = this.registerNewClient(new ClientView());

                    if (successfullyRegistered) {
                        System.out.println("Cliente cadastrado com sucesso!");
                    } else {
                        System.out.println("Cliente já estava previamente cadastrado! Nenhuma alteração foi realizada.");
                    }
                    break;
                case 2:     // logar
                    Map.Entry<String, String> credentials = bankView.getCredentials();

                    Client client = getClientFromCredentials(credentials);

                    if (client == null) {
                        System.out.println("Credenciais incorretas, tente novamente.");
                        return;
                    }

                    login(client);
                    break;
                case 3:     // sair
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    boolean registerNewClient(ClientView clientView) {
        Client client = clientView.buildANewClient();
        return this.clients.add(client);
    }

    void login(Client client) {
        BankView bankView = new BankView();

        while (true) {
            bankView.showLoggedMenu(client);

            int option = bankView.getOptionFromUser();

            switch (option) {
                case 1: //depositar
                    break;
                case 2: // sacar
                    break;
                case 3: // transferir
                    break;
                case 4: // deslogar
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }
    }

    private Client getClientFromCredentials(Map.Entry<String, String> credentials) {

        Client client = this.clients.stream().
                                     filter(c -> c.cpf.equals(credentials.getKey())).
                                     findAny().
                                     orElse(null);

        if (client != null && client.passwordIsEqualTo(credentials.getValue())) {
            return client;
        }

        return null;
    }
}
