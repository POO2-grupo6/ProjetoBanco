package sinqia.view;

import sinqia.client.Client;
import sinqia.client.NaturalPerson;
import sinqia.exceptions.BlankFieldException;
import sinqia.exceptions.InvalidAmountException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankView {
	private Scanner scanner = new Scanner(System.in);
	
	public void showAccountAlreadyExistsMessage() {
		System.out.println("A conta já existe.");
	}

	public void showClientAlreadyRegisteredMessage() {
		System.out.println("Já há um cliente registrado com este CPF/CNPJ.");
	}

	public void showClientSuccessfullyRegisteredMessage() {
		System.out.println("Cliente registrado com sucesso!");
	}

	public void showAccountSuccessfullyActivatedMessage(long accountNumber) {
		System.out.println("Conta de número " + accountNumber + " criada com sucesso!");
	}

	public String showMainMenu() throws InterruptedException {
		Thread.sleep(1000);
		System.out.println();
		System.out.println("======================================");
		System.out.println("====== Bem-vindo ao Banco Gr-6 =======");
		System.out.println("======================================");
		System.out.println("|         Escolha uma opção:         |");
		System.out.println("|    1 - Registrar novo cliente PF   |");
		System.out.println("|    2 - Registrar novo cliente PJ   |");
		System.out.println("|    3 - Entrar                      |");
		System.out.println("|    4 - Listar clientes             |");
		System.out.println("|    5 - Fechar                      |");
		System.out.println("======================================");

		return scanner.nextLine().toUpperCase();
	}
	
	public List<String> loginScreen(){
		List<String> loginCredentials = new ArrayList<>();
		System.out.print("Insira o CPF/CNPJ: ");
		loginCredentials.add(scanner.nextLine());
		System.out.print("Insira a senha: ");
		loginCredentials.add(scanner.nextLine());
		return loginCredentials;
	}

    public void showAccountBalance(long accountNumber, BigDecimal balance) {
		System.out.format("O saldo da conta %d é de R$ %,.2f.", accountNumber, balance);
		System.out.println();
    }

	public BigDecimal getAmountFromUser() {
		System.out.print("Informe o valor: R$ ");
		BigDecimal amount;

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

	public void showSuccessfulWithdrawMessage(BigDecimal newBalance) {
		System.out.println("Saque realizado com sucesso!");
		System.out.format("O novo saldo da conta é de R$ %,.2f.", newBalance);
		System.out.println();
	}

	public void showInsufficientFundsMessage() {
		System.out.println("Saldo insuficiente.");
	}

	public long getDestinationAccountNumberFromUser() {
		System.out.print("Informe a numeração da conta de destino: ");
		return Long.parseLong(scanner.nextLine());  
	}

	public void showSuccessfulDepositMessage(BigDecimal newBalance) {
		System.out.println("Depósito realizado com sucesso!");
		System.out.format("O novo saldo da conta é de R$ %,.2f.", newBalance);
		System.out.println();
	}
	
	public void showSuccessfulInvestmentMessage(BigDecimal newBalance) {
		System.out.println("Investimento realizado com sucesso!");
		System.out.format("O novo saldo da conta é de R$ %,.2f.", newBalance);
		System.out.println();
	}
	
	public void showSuccessfulRedeemMessage(BigDecimal newBalance) {
		System.out.println("Resgate realizado com sucesso!");
		System.out.format("O novo saldo da conta é de R$ %,.2f.", newBalance);
		System.out.println();
	}
	
	public void showSuccessfulTransferMessage(BigDecimal newBalance) {
		System.out.println("Transferência realizada com sucesso!");
		System.out.format("O novo saldo da conta é de R$ %,.2f.", newBalance);
		System.out.println();
	}
	
	public BigDecimal transferScreenAmount(){
		System.out.print("Insira o valor de transferência: R$ ");
		BigDecimal amount = scanner.nextBigDecimal();
		scanner.nextLine();
		return amount;
	}

	public long transferScreenAccount() {
		System.out.print("Insira a conta destino: ");
		return Long.parseLong(scanner.nextLine());
	}

	public void showInvalidAmountInputMessage() {
		System.out.println("Por favor, informe valores com o seguinte formato de exemplo: 6.543,21.");
	}

	public void showInvalidAmountMessage() {
		System.out.println("O valor mínimo é de R$ 0,01.");
	}

    public void showAccountNotFoundMessage() {
		System.out.println("Conta não localizada.");
    }

	public void showInvalidInputForAccountMessage() {
		System.out.println("Por favor, informe apenas números.");
	}

	public int showInvestmentAccountDoesNotExistMessage() {
		System.out.println("Você ainda não possui uma conta investimento!\nDeseja abrir uma?");
		System.out.println("1 - Sim\n2 - Não");
		return scanner.nextInt();
	}

	public String getNotBlankInputFromUser() {
		String string = scanner.nextLine();

		if (string.isBlank()) {
			throw new BlankFieldException();
		}

		return string;
	}

	public void showFieldCanNotBeBlankMessage() {
		System.out.println("O campo não pode ficar em branco.");
	}

	public int showInvestmentAccountMenu(Client client) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println("1 - Ver saldo\n"
				+ "2 - Resgatar\n"
				+ "3 - Investir\n"
				+ "4 - Acessar conta corrente");
		if(client instanceof NaturalPerson) {
			System.out.println("5 - Acessar conta poupança.");
		}
		return scanner.nextInt();
	}

	public void promptUserToOpenAccount() {
		System.out.println("A conta ainda não existe!\nDeseja abrir uma?");
		System.out.println("1 - Sim.\n2 - Não.");
	}

	public void showInvalidOptionMessage() {
		System.out.println("Opção inválida.");
	}

	public int getOptionFromUser() {
		return Integer.parseInt(scanner.nextLine());
	}
}
