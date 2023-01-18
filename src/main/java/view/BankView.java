package main.java.view;

import java.math.BigDecimal;
import java.util.Scanner;

public class BankView { //singleton?
    public void showGreetingsMessage() {
        System.out.println("Olá! Seja bem-vindo ao Banco!");
    }

    public void showStartMenu() {
        System.out.println();
        System.out.println("escolha uma opção:");
        System.out.println("1 - cadastrar novo cliente pessoa física");
        System.out.println("2 - cadastrar novo cliente pessoa jurídica");
        System.out.println("3 - efetuar login");
        System.out.println("4 - depositar");
        System.out.println("5 - sair");
    }

    public void showLoggedMenuForNaturalPersons(String name) {
        System.out.println();
        System.out.println("Olá, " + name + "!");
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
    }

    public void showLoggedMenuForJuridicalPersons(String name) {
        System.out.println();
        System.out.println("É um prazer trabalhar com a empresa " + name + "!");
        System.out.println("escolha uma opção:");
        System.out.println(" 1 - abrir conta-corrente");
        System.out.println(" 2 - abrir conta-investimento");
        System.out.println(" 3 - sacar");
        System.out.println(" 4 - transferir");
        System.out.println(" 5 - depositar");
        System.out.println(" 6 - investir");
        System.out.println(" 7 - resgatar investimento");
        System.out.println(" 8 - consultar saldo da conta-corrente");
        System.out.println(" 9 - consultar saldo da conta-investimento");
        System.out.println("10 - consultar saldo total");
        System.out.println("11 - deslogar");
    }

    private void showSuccessfulClientRegistrationMessage() {
        System.out.println("Cliente cadastrado com sucesso!");
    }

    private void showFailedClientRegistrationMessage() {
        System.out.println("Cliente já estava previamente cadastrado! Nenhuma alteração foi realizada.");
    }

    public void showFarewellMessage() {
        System.out.println("Até logo!");
    }

    public int getOptionFromUser() {
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void showFailedLoginMessage() {
        System.out.println("Credenciais incorretas, tente novamente.");
    }

    public void showInvalidOptionMessage() {
        System.out.println("Opção inválida, tente novamente.");
    }

    public void showResultOfRegistrationAttempt(boolean successfullyRegistered) {
        if (successfullyRegistered) {
            this.showSuccessfulClientRegistrationMessage();
        } else {
            this.showFailedClientRegistrationMessage();
        }
    }

    public BigDecimal getValueFromUser() {
        System.out.print("informe o valor: R$ ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextBigDecimal();
    }

    public int getDestinationAccountNumberFromUser() {
        System.out.print("informe o número da conta de destino: ");
        Scanner scanner = new Scanner(System.in);
        return scanner.nextInt();
    }

    public void showBalance(BigDecimal balance) {
        System.out.format("O saldo é de R$ %,.2f.", balance);
        System.out.println();
    }

    public void showSuccessfulAccountOpeningMessage(int accountNumber) {
        System.out.println("A conta foi aberta com sucesso! O número da conta é:");
        System.out.println(accountNumber);
        System.out.println("Guarde este dado.");
    }

    public void showSuccessfulOperationMessage(BigDecimal newBalance) { // talvez separar em 2 mensagens
        System.out.format("Operação realizada com sucesso. O novo saldo da conta é de R$ %,.2f.%n", newBalance);
    }

    public void showAccountTypeSelectionMenu(String name) {
        System.out.println("Olá, " + name + "!");
        System.out.println("Por favor escolha escolha uma opção:");
        System.out.println("1 - depositar");
        System.out.println("2 - acessar conta-corrente");
        System.out.println("3 - acessar conta-poupança");
        System.out.println("4 - acessar conta investimento");
    }

    public void showCheckingAccountMenu() {
        System.out.println("escolha uma opção:");
        System.out.println("1 - sacar");
        System.out.println("2 - transferir");
        System.out.println("3 - depositar nesta conta");
        System.out.println("4 - consultar saldo");
        System.out.println("5 - retornar ao menu anterior / escolher outro tipo de conta");
    }

    public void showSavingsAccountMenu() {
        System.out.println("escolha uma opção:");
        System.out.println("1 - sacar");
        System.out.println("2 - transferir");
        System.out.println("3 - depositar nesta conta");
        System.out.println("4 - consultar saldo");
        System.out.println("5 - retornar ao menu anterior / escolher outro tipo de conta");
    }

    public void showInvestmentAccountMenu() {
        System.out.println("escolha uma opção:");
        System.out.println("1 - investir");
        System.out.println("2 - resgatar investimento");
        System.out.println("3 - consultar saldo");
        System.out.println("4 - retornar ao menu anterior / escolher outro tipo de conta");
    }
}
