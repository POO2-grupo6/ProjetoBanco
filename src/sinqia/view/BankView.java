package sinqia.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankView {
	
	Scanner scanner = new Scanner(System.in);
	
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
		List<String> loginCredentials = new ArrayList<String>();
		System.out.print("Insira o CPF/CNPJ: ");
		loginCredentials.add(scanner.nextLine());
		System.out.print("Insira a senha: ");
		loginCredentials.add(scanner.nextLine());
		return loginCredentials;
	}
}