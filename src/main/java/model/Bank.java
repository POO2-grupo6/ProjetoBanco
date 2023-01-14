package main.java.model;

import main.java.view.BankView;
import main.java.view.ClientView;
import main.java.view.JuridicalPersonClientView;
import main.java.view.NaturalPersonClientView;

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
                case 1: {    // fazer enum? cadastrar novo cliente PF
                    boolean successfullyRegistered = this.registerNewClient(new NaturalPersonClientView());

                    if (successfullyRegistered) {  // acho que mensagem ficaria melhor no próprio registerNewClient
                        System.out.println("Cliente cadastrado com sucesso!");
                    } else {
                        System.out.println("Cliente já estava previamente cadastrado! Nenhuma alteração foi realizada.");
                    }

                    break;
                }
                case 2: {    // fazer enum? cadastrar novo cliente PJ
                    boolean successfullyRegistered = this.registerNewClient(new JuridicalPersonClientView());

                    if (successfullyRegistered) {
                        System.out.println("Cliente cadastrado com sucesso!");
                    } else {
                        System.out.println("Cliente já estava previamente cadastrado! Nenhuma alteração foi realizada.");
                    }

                    break;
                }
                case 3:     // logar
                    Map.Entry<String, String> credentials = ClientView.getCredentials();

                    Client client = getClientFromCredentials(credentials);

                    if (client == null) {
                        System.out.println("Credenciais incorretas, tente novamente.");
                        break;
                    }

                    login(client);
                    break;
                case 4:     // sair
                    bankView.showFarewellMessage();
                    return;
                default:
                    System.out.println("Opção inválida, tente novamente.");
            }
        }

    }

    private boolean registerNewClient(ClientView clientView) {
        Client client = clientView.buildANewClient();
        return this.clients.add(client);
    }

    void login(Client client) {
        BankView bankView = new BankView();

        while (true) {
            bankView.showLoggedMenu(client.getName());

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
                                     filter(c -> c.registrationId.equals(credentials.getKey())).
                                     findAny().
                                     orElse(null);

        if (client != null && client.passwordIsEqualTo(credentials.getValue())) {
            return client;
        }

        return null;
    }
}
