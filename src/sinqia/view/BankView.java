package sinqia.view;

import sinqia.client.Client;
import sinqia.client.NaturalPerson;
import sinqia.exceptions.BlankFieldException;
import sinqia.exceptions.InvalidAmountException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
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
	
	public List<String> getClientCredentials(){
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

	public void showSuccessfulOperationMessage(BigDecimal newBalance) {
		System.out.println("Operação realizado com sucesso!");
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

	public void showInvestmentAccountDoesNotExistMessage() {
		System.out.println("Você ainda não possui uma conta investimento!\nDeseja abrir uma?");
		System.out.println("1 - Sim\n2 - Não");
	}

	private String getNonBlankInputFromUser() {
		String string = scanner.nextLine();

		if (string.isBlank()) {
			throw new BlankFieldException();
		}

		return string;
	}

	public void showFieldCanNotBeBlankMessage() {
		System.out.println("O campo não pode ficar em branco.");
	}

	public void showInvestmentAccountMenu(Client client) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println();
		System.out.print("=".repeat(18 - client.getName().length() / 2));
		System.out.print(" Olá, " + client.getName() + " ");
		System.out.print("=".repeat(18 - client.getName().length() / 2));
		System.out.print(client.getName().length() % 2 == 0 ? "=" : "");
		System.out.println();
		System.out.println("|             Escolha uma opção:           |");
		System.out.println("|          1 - Consultar saldo             |");
		System.out.println("|          2 - Resgatar                    |");
		System.out.println("|          3 - Investir                    |");
		System.out.println("|          4 - Acessar conta corrente      |");
		System.out.println("|          5 - Deslogar                    |");
		if(client instanceof NaturalPerson) {
			System.out.println("|          6 - Acessar conta poupança      |");
		}
		System.out.println("============================================");
	}

	public void showSavingsAccountMenu(String name) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println();
		System.out.print("=".repeat(18 - name.length() / 2));
		System.out.print(" Olá, " + name + " ");
		System.out.print("=".repeat(18 - name.length() / 2));
		System.out.print(name.length() % 2 == 0 ? "=" : "");
		System.out.println();
		System.out.println("|             Escolha uma opção:           |");
		System.out.println("|          1 - Consultar saldo             |");
		System.out.println("|          2 - Acessar conta investimento  |");
		System.out.println("|          3 - Acessar conta corrente      |");
		System.out.println("|          4 - Sacar                       |");
		System.out.println("|          5 - Depositar                   |");
		System.out.println("|          6 - Transferir                  |");
		System.out.println("|          7 - Deslogar                    |");
		System.out.println("============================================");
	}

	public void promptUserToOpenAccount() {
		System.out.println("A conta ainda não existe!\nDeseja abrir uma?");
		System.out.println("1 - Sim\n2 - Não");
	}

	public void showInvalidOptionMessage() {
		System.out.println("Opção inválida.");
	}

	public int getOptionFromUser() {
		int selectedOption = 0;
		try {
			selectedOption = Integer.parseInt(scanner.nextLine());
		} 
			catch (InputMismatchException | NumberFormatException e) {
			showInvalidInputForAccountMessage();
		}	
		return selectedOption;
	}

	public void showCheckingAccountMenuWithSavingsAccount(String name) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println();
		System.out.print("=".repeat(18 - name.length() / 2));
		System.out.print(" Olá, " + name + " ");
		System.out.print("=".repeat(18 - name.length() / 2));
		System.out.print(name.length() % 2 == 0 ? "=" : "");
		System.out.println();
		System.out.println("|             Escolha uma opção:           |");
		System.out.println("|          1 - Consultar saldo             |");
		System.out.println("|          2 - Acessar conta investimento  |");
		System.out.println("|          3 - Acessar conta poupança      |");
		System.out.println("|          4 - Sacar                       |");
		System.out.println("|          5 - Depositar                   |");
		System.out.println("|          6 - Transferir                  |");
		System.out.println("|          7 - Deslogar                    |");
		System.out.println("============================================");
	}

	public void showCheckingAccountMenuWithoutSavingsAccount(String name) throws InterruptedException {
		Thread.sleep(1000);
		System.out.println();
		System.out.print("=".repeat(18 - name.length() / 2));
		System.out.print(" Olá, " + name + " ");
		System.out.print("=".repeat(18 - name.length() / 2));
		System.out.print(name.length() % 2 == 0 ? "=" : "");
		System.out.println();
		System.out.println("|             Escolha uma opção:           |");
		System.out.println("|          1 - Consultar saldo             |");
		System.out.println("|          2 - Acessar conta investimento  |");
		System.out.println("|          3 - Sacar                       |");
		System.out.println("|          4 - Depositar                   |");
		System.out.println("|          5 - Transferir                  |");
		System.out.println("|          6 - Deslogar                    |");
		System.out.println("============================================");
	}

    public void showAccountDoesNotAcceptTransferMessage() {
		System.out.println("A conta de destino não recebe recursos via transferência.");
    }

	public void showDestinationAndOriginAccountCanNotBeTheSameMessage() {
		System.out.println("Você não pode transferir para a mesma conta!");
	}

	public String[] getNaturalPersonRegistrationData() {
		System.out.print("Insira o nome: ");
		String name = getNonBlankInputFromUser();

		System.out.print("Insira o CPF: ");
		String registrationId = getNonBlankInputFromUser();

		System.out.print("Crie uma senha: ");
		String password = getNonBlankInputFromUser();

		return new String[]{name, registrationId, password};
	}

	public String[] getJuridicalPersonRegistrationData() {
		System.out.print("Insira o nome: ");
		String name = getNonBlankInputFromUser();

		System.out.print("Insira o CNPJ: ");
		String registrationId = getNonBlankInputFromUser();

		System.out.print("Crie uma senha: ");
		String password = getNonBlankInputFromUser();

		return new String[]{name, registrationId, password};
	}

	public void showNoClientsRegisteredYetMessage() {
		System.out.println("Ainda não há clientes cadastrados.");
	}

	public void showFarewellMessage() {
		System.out.println("Até mais!");
	}

	public void showExceptionMessage(String message) {
		System.out.println(message);
	}
}
