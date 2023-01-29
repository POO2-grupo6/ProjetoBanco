package sinqia.view;

import sinqia.exceptions.InvalidAmountException;

import java.math.BigDecimal;
import java.util.Scanner;

public abstract class AccountMenu implements IAccountMenu {
    protected AccountMenu() {
    }

    public static void showAccountSuccessfullyActivatedMessage(long accountNumber) {
        System.out.println("Conta de número " + accountNumber + " criada com sucesso!");
    }

    public static void showAccountBalance(long accountNumber, BigDecimal balance) {
        System.out.format("O saldo da conta %d é de R$ %,.2f.", accountNumber, balance);
        System.out.println();
    }

    public static BigDecimal getAmountFromUser() {
        System.out.print("Informe o valor: R$ ");
        BigDecimal amount;

        Scanner scanner = new Scanner(System.in);

        try {
            amount = scanner.nextBigDecimal();
        } finally {
            scanner.nextLine();
        }

        if (amount.compareTo(BigDecimal.valueOf(0.01)) < 0) {
            throw new InvalidAmountException();
        }

        return amount;
    }

    public static void showSuccessfulOperationMessage(BigDecimal newBalance) {
        System.out.println("Operação realizado com sucesso!");
        System.out.format("O novo saldo da conta é de R$ %,.2f.", newBalance);
        System.out.println();
    }

    public static void showInsufficientFundsMessage() {
        System.out.println("Saldo insuficiente.");
    }

    public static long getDestinationAccountNumberFromUser() {
        System.out.print("Informe a numeração da conta de destino: ");
        Scanner scanner = new Scanner(System.in);
        return Long.parseLong(scanner.nextLine());
    }

    public static void showInvalidAmountInputMessage() {
        System.out.println("Por favor, informe valores com o seguinte formato de exemplo: 6.543,21.");
    }

    public static void showInvalidAmountMessage() {
        System.out.println("O valor mínimo é de R$ 0,01.");
    }

    public static void showAccountNotFoundMessage() {
        System.out.println("Conta não localizada.");
    }

    public static void showInvalidInputForAccountMessage() {
        System.out.println("Por favor, informe apenas números.");
    }

    public static void promptUserToOpenAccount() {
        System.out.println("A conta ainda não existe!\nDeseja abrir uma?");
        System.out.println("1 - Sim\n2 - Não");
    }

    public static void showAccountAlreadyExistsMessage() {
        System.out.println("A conta já existe.");
    }
}
