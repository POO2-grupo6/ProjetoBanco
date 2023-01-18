package main.java.model.bank;

import main.java.model.account.Account;
import main.java.model.client.Client;
import main.java.model.client.JuridicalPersonClient;
import main.java.model.client.NaturalPersonClient;
import main.java.model.client.Person;
import main.java.view.BankView;
import main.java.view.ClientView;
import main.java.view.JuridicalPersonClientView;
import main.java.view.NaturalPersonClientView;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.function.Predicate;

public class Bank { // talvez criar BankController
    private int numberOfAccounts = 0;
    private Set<Client> clients = new HashSet<>();

    public void run(Person person, BankView bankView) {
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
                case 4:  // depositar
                    int destinationAccountNumber = bankView.getDestinationAccountNumberFromUser();
                    Account destinationAccount = getAccountFromAccountNumber(destinationAccountNumber);

                    if (destinationAccount == null) {
                        throw new AccountNotFoundException();
                    }

                    BigDecimal valueToDeposit = bankView.getValueFromUser();
                    person.deposit(destinationAccount, valueToDeposit);
                    break;
                case 5:     // sair
                    bankView.showFarewellMessage();
                    return;
                default:
                    bankView.showInvalidOptionMessage();
            }
        }
    }

    private boolean registerNewClient(ClientView clientView) {
        Client client = clientView.buildANewClient();
        client.openCheckingAccount(this.numberOfAccounts + 1);
        this.numberOfAccounts++;
        return this.clients.add(client);
    }

    private void loginNaturalPerson(NaturalPersonClient client) {
        BankView bankView = new BankView();  // usar variável estática?

        while (true) {
            bankView.showAccountTypesForNaturalPersonsSelectionMenu(client.getName());

            int accountTypeOption = bankView.getOptionFromUser();
            switch (accountTypeOption) {
                case 1:  // depositar
                    int destinationAccountNumber = bankView.getDestinationAccountNumberFromUser();
                    Account destinationAccount = getAccountFromAccountNumber(destinationAccountNumber);

                    if (destinationAccount == null) {
                        throw new AccountNotFoundException();
                    }

                    BigDecimal valueToDeposit = bankView.getValueFromUser();
                    client.deposit(destinationAccount, valueToDeposit);
                    break;
                case 2:  // acessar conta-corrente
                    loginCheckingAccount(client, bankView);
                    break;
                case 3:  // acessar conta-poupança
                    if (client.getSavingsAccount() == null) {
                        bankView.promptAccountOpening();

                        int option = bankView.getOptionFromUser();

                        if (option == 1) {
                            // extrair para método
                            client.openSavingsAccount(this.numberOfAccounts + 1);
                            this.numberOfAccounts++;
                            bankView.showSuccessfulAccountOpeningMessage(this.numberOfAccounts);
                        } else if (option == 2) {
                            break;
                        }
                    }

                    loginSavingsAccount(client, bankView);
                    break;
                case 4:  // acessar conta-investimento
                    loginInvestmentAccount(client, bankView);
                    break;
                case 5:  // deslogar
                    return;
                default:
                    bankView.showInvalidOptionMessage();
            }

//            bankView.showLoggedMenuForNaturalPersons(client.getName());
//
//            int option = bankView.getOptionFromUser();
//            switch (option) {
//                case 1: // abrir conta-corrente; extrair para método em Client, "openCheckingAccount"; informar número da conta préexistente
//                    client.openCheckingAccount(this.numberOfAccounts + 1);
//                    this.numberOfAccounts++;  // poderia ter sido usado ++number, mas acho desnecessário
//                    bankView.showSuccessfulAccountOpeningMessage(this.numberOfAccounts);  // seria melhor pegar o número direto da conta? Esta mensagem deveria estar em Client?
//                    break;
//                case 2:  // abrir conta-poupança
//                    client.openSavingsAccount(this.numberOfAccounts + 1);
//                    this.numberOfAccounts++;
//                    bankView.showSuccessfulAccountOpeningMessage(this.numberOfAccounts);
//                    break;
//                case 3:  // abrir conta-investimento
//                    client.openInvestmentAccount(this.numberOfAccounts + 1,
//                                                 InvestmentAccount.INTEREST_FOR_NATURAL_PERSONS);
//                    this.numberOfAccounts++;
//                    bankView.showSuccessfulAccountOpeningMessage(this.numberOfAccounts);
//                    break;
//                case 4: {  // sacar da conta-corrente
//                    break;
//                }
//                case 5: {  // sacar da conta-poupança
//                    break;
//                }
//                case 6: {  // transferir a partir da conta-corrente
//                    break;
//                }
//                case 7: {  // transferir a partir da conta-poupança
//                    break;
//                }
//                case 8:  // depositar
//                    int destinationAccountNumber = bankView.getDestinationAccountNumberFromUser();
//                    Account destinationAccount = getAccountFromAccountNumber(destinationAccountNumber);
//
//                    if (destinationAccount == null) {
//                        throw new AccountNotFoundException();
//                    }
//
//                    BigDecimal valueToDeposit = bankView.getValueFromUser();
//                    client.deposit(destinationAccount, valueToDeposit);
//                    break;
//                case 9: {  // investir
//                    break;
//                }
//                case 10:  // resgatar investimento
//                    break;
//                case 11: {  // consultar saldo da conta-corrente
//                    BigDecimal balance = client.getBalanceFromAccount(client.getCheckingAccount());
//                    bankView.showBalance(balance);
//                    break;
//                }
//                case 12: {  // consultar saldo da conta-poupança
//                    break;
//                }
//                case 13: {  // consultar saldo da conta-investimento
//                    break;
//                }
//                case 14:  // consultar saldo total
//                    BigDecimal balance = client.getTotalBalance();
//                    bankView.showBalance(balance);
//                    break;
//                case 15:  // deslogar
//                    return;
//                default:
//                    bankView.showInvalidOptionMessage();
//            }
        }
    }

    private void loginJuridicalPerson(JuridicalPersonClient client) {
        BankView bankView = new BankView();  // usar variável estática?

        while (true) {
            bankView.showAccountTypesForJuridicalPersonsSelectionMenu(client.getName());

            int accountTypeOption = bankView.getOptionFromUser();  // talvez mudar para loggedClientOptions() ou algo assim
            switch (accountTypeOption) {
                case 1:  // depositar
                    int destinationAccountNumber = bankView.getDestinationAccountNumberFromUser();
                    Account destinationAccount = getAccountFromAccountNumber(destinationAccountNumber);

                    if (destinationAccount == null) {
                        throw new AccountNotFoundException();
                    }

                    BigDecimal valueToDeposit = bankView.getValueFromUser();
                    client.deposit(destinationAccount, valueToDeposit);
                    break;
                case 2:  // acessar conta-corrente
                    loginCheckingAccount(client, bankView);
                    break;
                case 3:  // acessar conta-investimento
                    loginInvestmentAccount(client, bankView);
                    break;
                case 4:  // deslogar
                    return;
                default:
                    bankView.showInvalidOptionMessage();
            }



        /*

        BankView bankView = new BankView();

        while (true) {
            bankView.showLoggedMenuForJuridicalPersons(client.getName());

            int option = bankView.getOptionFromUser();
            switch (option) {
                case 1: // abrir conta-corrente
                    if (client.getCheckingAccount() == null) {
                        client.setCheckingAccount(new CheckingAccount(this.numberOfAccounts + 1));
                        this.numberOfAccounts++;
                    } else {
                        throw new AccountAlreadyExistsException();
                    }
                    break;
                case 2:  // abrir conta-investimento
                    if (client.getInvestmentAccount() == null) {
                        client.setInvestmentAccount(new InvestmentAccount(this.numberOfAccounts + 1,
                                InvestmentAccount.INTEREST_FOR_JURIDICAL_PERSONS));

                        this.numberOfAccounts++;
                    } else {
                        throw new AccountAlreadyExistsException();
                    }
                    break;
                case 3: {  // sacar
                    BigDecimal valueToWithdraw = bankView.getValueFromUser();
                    client.withdraw(client.getCheckingAccount(), valueToWithdraw);
                    BigDecimal newBalance = client.getCheckingAccount().getBalance();
                    bankView.showSuccessfulOperationMessage(newBalance);
                    break;
                }
                case 4: {  // transferir
                    int destinationAccountNumber = bankView.getDestinationAccountNumberFromUser();
                    Account destinationAccount = getAccountFromAccountNumber(destinationAccountNumber);

                    if (destinationAccount == null) {
                        throw new AccountNotFoundException();  // talvez dentro do método getAccountFromAccountNumber
                    }

                    BigDecimal valueToTransfer = bankView.getValueFromUser();
                    client.transfer(client.getCheckingAccount(), destinationAccount, valueToTransfer);

                    BigDecimal newBalance = client.getCheckingAccount().getBalance();
                    bankView.showSuccessfulOperationMessage(newBalance);
                    break;
                }
                case 5:  // depositar
                    int destinationAccountNumber = bankView.getDestinationAccountNumberFromUser();
                    Account destinationAccount = getAccountFromAccountNumber(destinationAccountNumber);

                    if (destinationAccount == null) {
                        throw new AccountNotFoundException();
                    }

                    BigDecimal valueToDeposit = bankView.getValueFromUser();
                    client.deposit(destinationAccount, valueToDeposit);
                    break;
                case 6: {  // investir
                    BigDecimal valueToInvest = bankView.getValueFromUser();
                    client.invest(valueToInvest);

                    BigDecimal newBalance = client.getInvestmentAccount().getBalance();
                    bankView.showSuccessfulOperationMessage(newBalance);
                    break;
                }
                case 7:  // resgatar investimento
                    BigDecimal valueToWithdraw = bankView.getValueFromUser();
                    client.withdrawFromInvestment(valueToWithdraw);

                    BigDecimal newBalance = client.getInvestmentAccount().getBalance();
                    bankView.showSuccessfulOperationMessage(newBalance);
                    break;
                case 8: {  // consultar saldo da conta-corrente
                    BigDecimal balance = client.getBalanceFromAccount(client.getCheckingAccount());
                    bankView.showBalance(balance);
                    break;
                }
                case 9: {  // consultar saldo da conta-investimento
                    BigDecimal balance = client.getBalanceFromAccount(client.getInvestmentAccount());
                    bankView.showBalance(balance);
                    break;
                }
                case 10:  // consultar saldo total
                    BigDecimal balance = client.getTotalBalance();
                    bankView.showBalance(balance);
                    return;
                case 11:  // deslogar
                    return;
                default:
                    bankView.showInvalidOptionMessage();
            }

            */


        }
    }

    private void loginInvestmentAccount(Client client, BankView bankView) {
        bankView.showInvestmentAccountMenu();

        int operationOption = bankView.getOptionFromUser();
        switch (operationOption) {
            case 1: {  // investir
                BigDecimal valueToInvest = bankView.getValueFromUser();
                client.invest(valueToInvest);  // tem que ver se as contas sao nulas
                BigDecimal newBalance = client.getInvestmentAccount().getBalance();
                bankView.showSuccessfulOperationMessage(newBalance);
                break;
            }
            case 2:  // resgatar investimento
                BigDecimal valueToWithdraw = bankView.getValueFromUser();
                client.withdrawFromInvestment(valueToWithdraw);

                BigDecimal newBalance = client.getInvestmentAccount().getBalance();
                bankView.showSuccessfulOperationMessage(newBalance);
                break;
            case 3:  // consultar saldo
                BigDecimal balance = client.getBalanceFromAccount(client.getInvestmentAccount());
                bankView.showBalance(balance);
                break;
            case 4:  // retornar ao menu anterior / escolher outro tipo de conta
                return;
            default:
                bankView.showInvalidOptionMessage();
        }

        this.loginInvestmentAccount(client, bankView);
    }

    private void loginSavingsAccount(NaturalPersonClient client, BankView bankView) {
        bankView.showSavingsAccountMenu();

        int operationOption = bankView.getOptionFromUser();
        switch (operationOption) {
            case 1: {  // sacar
                BigDecimal valueToWithdraw = bankView.getValueFromUser();
                client.withdraw(client.getSavingsAccount(), valueToWithdraw);
                BigDecimal newBalance = client.getSavingsAccount().getBalance();
                bankView.showSuccessfulOperationMessage(newBalance);
                break;
            }
            case 2: {  // transferir
                int destinationAccountNumber = bankView.getDestinationAccountNumberFromUser();
                Account destinationAccount = getAccountFromAccountNumber(destinationAccountNumber);

                if (destinationAccount == null) {
                    throw new AccountNotFoundException();
                }

                BigDecimal valueToTransfer = bankView.getValueFromUser();
                client.transfer(client.getSavingsAccount(), destinationAccount, valueToTransfer);

                BigDecimal newBalance = client.getSavingsAccount().getBalance();
                bankView.showSuccessfulOperationMessage(newBalance);
                break;
            }
            case 3:  // depositar nesta conta
                Account destinationAccount = client.getSavingsAccount();

                if (destinationAccount == null) {
                    throw new AccountNotFoundException();
                }

                BigDecimal valueToDeposit = bankView.getValueFromUser();
                client.deposit(destinationAccount, valueToDeposit);
                break;
            case 4:  // consultar
                BigDecimal balance = client.getBalanceFromAccount(client.getSavingsAccount());
                bankView.showBalance(balance);
                break;
            case 5:  // retornar ao menu anterior / escolher outro tipo de conta
                return;
            default:
                bankView.showInvalidOptionMessage();
        }

        this.loginSavingsAccount(client, bankView);
    }

    private void loginCheckingAccount(Client client, BankView bankView) {
        bankView.showCheckingAccountMenu();

        int operationOption = bankView.getOptionFromUser();
        switch (operationOption) {
            case 1: {  // sacar
                BigDecimal valueToWithdraw = bankView.getValueFromUser();
                client.withdraw(client.getCheckingAccount(), valueToWithdraw);
                BigDecimal newBalance = client.getCheckingAccount().getBalance();
                bankView.showSuccessfulOperationMessage(newBalance);
                break;
            }
            case 2: {  // transferir
                int destinationAccountNumber = bankView.getDestinationAccountNumberFromUser();
                Account destinationAccount = getAccountFromAccountNumber(destinationAccountNumber);

                if (destinationAccount == null) {
                    throw new AccountNotFoundException();
                }

                BigDecimal valueToTransfer = bankView.getValueFromUser();
                client.transfer(client.getCheckingAccount(), destinationAccount, valueToTransfer);

                BigDecimal newBalance = client.getCheckingAccount().getBalance();
                bankView.showSuccessfulOperationMessage(newBalance);
                break;
            }
            case 3:  // depositar nesta conta
                Account destinationAccount = client.getCheckingAccount();

                if (destinationAccount == null) {
                    throw new AccountNotFoundException();
                }

                BigDecimal valueToDeposit = bankView.getValueFromUser();
                client.deposit(destinationAccount, valueToDeposit);
                break;
            case 4: {  // consultar saldo
                BigDecimal balance = client.getBalanceFromAccount(client.getCheckingAccount());
                bankView.showBalance(balance);
                break;
            }
            case 5:  // retornar ao menu anterior / escolher outro tipo de conta
                return;
            default:
                bankView.showInvalidOptionMessage();
        }

        loginCheckingAccount(client, bankView);
    }

    private Account getAccountFromAccountNumber(int destinationAccountNumber) {
        Account destinationAccount;

        destinationAccount = this.clients.stream().  // talvez um map de client -> account, account, depois filtra os null
                                          map(Client::getCheckingAccount).
                                          filter(Objects::nonNull).
                                          filter(checkingAccount -> checkingAccount.getAccountNumber() == destinationAccountNumber).
                                          findAny().
                                          orElse(null);

        if (destinationAccount == null) {
            destinationAccount = this.clients.stream().
                                              map(Client::getInvestmentAccount).
                                              filter(Objects::nonNull).
                                              filter(investmentAccount -> investmentAccount.getAccountNumber() == destinationAccountNumber).
                                              findAny().
                                              orElse(null);
        }

        if (destinationAccount == null) {
            Predicate<NaturalPersonClient> predicate = client -> client.getSavingsAccount().
                                                                        getAccountNumber() == destinationAccountNumber;

            destinationAccount = this.clients.stream().
                                              filter(NaturalPersonClient.class::isInstance).
                                              map(NaturalPersonClient.class::cast).
                                              filter(client -> client.getSavingsAccount() != null).
                                              filter(predicate).
                                              map(NaturalPersonClient::getSavingsAccount).
                                              findAny().
                                              orElse(null);
        }

        return destinationAccount;
    }

    private Client getClientFromCredentials(Map.Entry<String, String> credentials) {
        Predicate<Client> validClient = client -> client.getRegistrationId().equals(credentials.getKey()) &&
                                                  client.passwordIsEqualTo(credentials.getValue());

        return this.clients.stream().
                            filter(validClient).
                            findAny().
                            orElse(null);
    }
}
