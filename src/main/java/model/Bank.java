package main.java.model;

import main.java.view.BankView;
import main.java.view.ClientView;
import main.java.view.JuridicalPersonClientView;
import main.java.view.NaturalPersonClientView;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Predicate;

public class Bank { // talvez criar BankController
    private int numberOfAccounts = 0;
    Set<Client> clients = new HashSet<>();

    public void run(BankView bankView) {
        bankView.showGreetingsMessage();

        while (true) {
            bankView.showStartMenu();
            int option = bankView.getOptionFromUser();

            switch (option) {
                case 1: {    // fazer enum? cadastrar novo cliente PF
                    boolean successfullyRegistered = this.registerNewClient(new NaturalPersonClientView());

                    bankView.showResultOfRegistrationAttempt(successfullyRegistered);

                    break;
                }
                case 2: {    // cadastrar novo cliente PJ
                    boolean successfullyRegistered = this.registerNewClient(new JuridicalPersonClientView());

                    bankView.showResultOfRegistrationAttempt(successfullyRegistered);

                    break;
                }
                case 3:     // logar
                    Map.Entry<String, String> credentials = ClientView.getCredentials();

                    Client client = getClientFromCredentials(credentials);

                    if (client instanceof NaturalPersonClient) {
                        loginNaturalPerson((NaturalPersonClient) client);
                    } else if (client instanceof JuridicalPersonClient) {
                        loginJuridicalPerson((JuridicalPersonClient) client);
                    } else {
                        bankView.showFailedLoginMessage();
                    }

                    break;
                case 4:     // sair
                    bankView.showFarewellMessage();
                    return;
                default:
                    bankView.showInvalidOptionMessage();
            }
        }
    }

    private boolean registerNewClient(ClientView clientView) {
        Client client = clientView.buildANewClient();
        return this.clients.add(client);
    }

    void loginNaturalPerson(NaturalPersonClient client) {
        BankView bankView = new BankView();

        while (true) {

            System.out.println("escolha uma opção:");
            System.out.println(" 1 - abrir conta-corrente");
            System.out.println(" 2 - abrir conta-poupança");
            System.out.println(" 3 - abrir conta-investimento");
            System.out.println(" 4 - sacar da conta-corrente");
            System.out.println(" 5 - sacar da conta-poupança");
            System.out.println(" 6 - transferir a partir da conta-corrente");
            System.out.println(" 7 - transferir a partir da conta-poupança");
            System.out.println(" 8 - depositar");
            System.out.println(" 9 - investir");  // equivalente a transferencia de conta corrente para conta investimento
            System.out.println("10 - resgatar investimento");  // equivalente a transferencia de conta investimento para conta corrente
            System.out.println("11 - consultar saldo da conta-corrente");
            System.out.println("12 - consultar saldo da conta-poupança");
            System.out.println("13 - consultar saldo da conta-investimento");
            System.out.println("14 - consultar saldo total");
            System.out.println("15 - deslogar");


            bankView.showLoggedMenuForNaturalPersons(client.getName());

            int option = bankView.getOptionFromUser();
            switch (option) {
                case 1: // abrir conta-corrente
                    if (client.checkingAccount == null) {
                        client.checkingAccount = new CheckingAccount(this.numberOfAccounts + 1);
                        this.numberOfAccounts++;
                    } else {
                        throw new AccountAlreadyExistsException();
                    }
                    break;
                case 2:  // abrir conta-poupança
                    if (client.savingsAccount == null) {
                        client.savingsAccount = new SavingsAccount(this.numberOfAccounts + 1);
                        this.numberOfAccounts++;
                    } else {
                        throw new AccountAlreadyExistsException();
                    }
                    break;
                case 3:  // abrir conta-investimento
                    if (client.investmentAccount == null) {
                        client.investmentAccount = new InvestmentAccount(this.numberOfAccounts + 1,
                                                                         InvestmentAccount.INTEREST_FOR_NATURAL_PERSONS);

                        this.numberOfAccounts++;
                    } else {
                        throw new AccountAlreadyExistsException();
                    }
                    break;
                case 4:  // sacar da conta-corrente
                    break;
                case 5:  // depositar
                    break;
                case 6:  // transferir
                    return;
                case 7:  // investir
                    break;
                case 8:  // consultar saldo
                    break;
                case 9:  // deslogar
                    return;
                default:
                    bankView.showInvalidOptionMessage();
            }
        }
    }

    void loginJuridicalPerson(JuridicalPersonClient client) {
        BankView bankView = new BankView();

        while (true) {
            bankView.showLoggedMenuForJuridicalPersons(client.getName());

            int option = bankView.getOptionFromUser();
            switch (option) {
                case 1: // abrir conta-corrente
                    if (client.checkingAccount == null) {
                        client.checkingAccount = new CheckingAccount(this.numberOfAccounts + 1);
                        this.numberOfAccounts++;
                    } else {
                        throw new AccountAlreadyExistsException();
                    }
                    break;
                case 3:  // abrir conta-investimento
                    if (client.investmentAccount == null) {
                        client.investmentAccount = new InvestmentAccount(this.numberOfAccounts + 1,
                                                                         InvestmentAccount.INTEREST_FOR_JURIDICAL_PERSONS);

                        this.numberOfAccounts++;
                    } else {
                        throw new AccountAlreadyExistsException();
                    }
                    break;
                case 4:  // sacar
                    break;
                case 5:  // depositar
                    break;
                case 6:  // transferir
                    return;
                case 7:  // investir
                    break;
                case 8:  // consultar saldo
                    break;
                case 9:  // deslogar
                    return;
                default:
                    bankView.showInvalidOptionMessage();
            }
        }
    }

    private Client getClientFromCredentials(Map.Entry<String, String> credentials) {
        Predicate<Client> validClient = client -> client.registrationId.equals(credentials.getKey()) &&
                                                  client.passwordIsEqualTo(credentials.getValue());

        return this.clients.stream().
                            filter(validClient).
                            findAny().
                            orElse(null);
    }
}
