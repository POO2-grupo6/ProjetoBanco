package sinqia.view;

import sinqia.client.Client;

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

	public String showMainMenu() {
		// MENU PRINCIPAL
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

    public void showAccountBalance(Long accountNumber, BigDecimal balance) {
		System.out.format("O saldo da conta %d é de R$ %,.2f", accountNumber, balance);
		System.out.println();
    }

	public BigDecimal getAmountFromUser() {
		System.out.print("informe o valor: R$ ");
		return scanner.nextBigDecimal();
	}

	public void showSuccessfullWithdrawMessage(BigDecimal newBalance) {
		System.out.println("Saque realizado com sucesso!");
		System.out.format("O novo saldo da conta é de R$ %,.2f", newBalance);
		System.out.println();
	}

	public void showInsufficientFundsMessage() {
		System.out.println("Saldo insuficiente.");
	}

	public BigDecimal transferScreenAmount(){
		System.out.println("Insira o valor de transferencia : ");
		return scanner.nextBigDecimal();

	}

	public int transferScreenAccount(){
		System.out.println("Insira a conta destino :");
		return scanner.nextInt();

	}


}