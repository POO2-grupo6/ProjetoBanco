package sinqia.view;

import sinqia.exceptions.BlankFieldException;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankView {
	private Scanner scanner = new Scanner(System.in);

	public void showClientAlreadyRegisteredMessage() {
		System.out.println("Já há um cliente registrado com este CPF/CNPJ.");
	}

	public void showClientSuccessfullyRegisteredMessage() {
		System.out.println("Cliente registrado com sucesso!");
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
	
	public List<String> getClientCredentials(){
		List<String> loginCredentials = new ArrayList<>();
		System.out.print("Insira o CPF/CNPJ: ");
		loginCredentials.add(scanner.nextLine());
		System.out.print("Insira a senha: ");
		loginCredentials.add(scanner.nextLine());
		return loginCredentials;
	}

	public void showInvestmentAccountDoesNotExistMessage() {
		System.out.println("Você ainda não possui uma conta investimento!\nDeseja abrir uma?");
		System.out.println("1 - Sim\n2 - Não");
	}

	public String getNonBlankInputFromUser() {
		String string = scanner.nextLine();

		if (string.isBlank()) {
			throw new BlankFieldException();
		}

		return string;
	}

	public void showFieldCanNotBeBlankMessage() {
		System.out.println("O campo não pode ficar em branco.");
	}

	public void showInvalidOptionMessage() {
		System.out.println("Opção inválida.");
	}

	public int getOptionFromUser() {
		return Integer.parseInt(scanner.nextLine());
	}
}
