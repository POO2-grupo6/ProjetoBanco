package sinqia;

import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import sinqia.account.CheckingAccount;
import sinqia.account.InvestmentAccount;
import sinqia.account.SavingsAccount;
import sinqia.client.Client;
import sinqia.client.JuridicalPerson;
import sinqia.client.NaturalPerson;
import sinqia.exceptions.ClientNotFoundException;
import sinqia.exceptions.PasswordMismatchException;
import sinqia.view.BankView;

public class Bank {
	private BankView bankView;
	private Scanner scanner = new Scanner(System.in);
	private Set<Client> clients;
	private Client currentClient;
	private long numberOfAccountsOpened = 0;

	public Bank(BankView bankView) {
		this.bankView = bankView;
		clients = new HashSet<>();
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void loadMainMenu() {
		String menu = bankView.showMainMenu();
		switch (menu) {
			case "1": 
				registerNewClient(new NaturalPerson());
				this.loadMainMenu();
				break;
			case "2":
				registerNewClient(new JuridicalPerson());
				this.loadMainMenu();
				break;
			case "3":
				login();
				break;
			case "4":
				listClients();
				break;
			case "5":
				System.out.println("Até mais!");
				break;
			default:
				System.out.println("Opção inválida.");
				loadMainMenu();
		}
	}

	private void login() {
		if(clients.isEmpty()) {
			System.out.println("Ainda não há clientes cadastrados.");
			this.loadMainMenu();
		}else {
			try{
				List<String> loginCredentials = bankView.loginScreen();
				currentClient = findClient(loginCredentials.get(0));
				currentClient.validatePassword(loginCredentials.get(1));
				this.loadClientMenu(currentClient);
			} catch (ClientNotFoundException | PasswordMismatchException e) {
				System.out.println(e.getMessage());
				this.loadMainMenu();
			}
		}
	}

	private Client findClient(String registrationId) throws ClientNotFoundException {
		for(Client client : clients) {
			if(client.getRegistrationId().equals(registrationId)) {
				return client;
			}
		}

		throw new ClientNotFoundException();
	}

	private void loadClientMenu(Client client) {
							// MENU DO CLIENTE
		System.out.print("=".repeat(18 - client.getName().length()/2));
		System.out.print(" Olá, " + client.getName() + " ");
		System.out.print("=".repeat(18 - client.getName().length()/2));
		System.out.print(client.getName().length() % 2 == 0 ? "=" : "");
		System.out.println();
		System.out.println("|             Escolha uma opção:           |");
		System.out.println("|          1 - Consultar saldo             |");
		System.out.println("|          2 - Abrir conta poupança        |");
		System.out.println("|          3 - Abrir conta Investimento    |");
		System.out.println("|          4 - Sacar                       |");
		System.out.println("|          5 - Depositar                   |");
		System.out.println("|          6 - Transferir                  |");
		System.out.println("|          7 - Investir                    |");
		System.out.println("|          8 - Deslogar                    |");
		System.out.println("============================================");

		String clientMenu = scanner.nextLine().toUpperCase();
		switch(clientMenu) {
			case "1":
//				checkBalance();
				break;
			case "2":
				activateInvestmentAccount(client);
				loadClientMenu(client);
				break;
			case "3":
				activateSavingsAccount((NaturalPerson) client);
				loadClientMenu(client);
				break;
			case "4":
//				withdraw();
				break;
			case "5":
//				deposit();
				break;
			case "6":
//				transfer();
				break;
			case "7":
//				invest();
				break;
			case "8":
				loadMainMenu();
				break;
			default:
				System.out.println("Opção inválida.");
				loadClientMenu(client);
		}
	}

	private void activateSavingsAccount(NaturalPerson client) {
		if (client.getSavingsAccount() == null) {
			client.setSavingsAccount(new SavingsAccount(numberOfAccountsOpened + 1));
			numberOfAccountsOpened++;
			bankView.showAccountSuccessfullyActivatedMessage(numberOfAccountsOpened);
		} else {
			bankView.showAccountAlreadyExistsMessage();
		}
	}

	private void activateInvestmentAccount(Client client) {
		if (client.getInvestmentAccount() == null) {
			client.setInvestmentAccount(new InvestmentAccount(numberOfAccountsOpened + 1, client.getInvestmentInterestRate()));
			numberOfAccountsOpened++;
			bankView.showAccountSuccessfullyActivatedMessage(numberOfAccountsOpened);
		} else {
			bankView.showAccountAlreadyExistsMessage();
		}
	}

	private void activateCheckingAccount(Client client) {
		if (client.getCheckingAccount() == null) {
			client.setCheckingAccount(new CheckingAccount(numberOfAccountsOpened + 1));
			numberOfAccountsOpened++;
			bankView.showAccountSuccessfullyActivatedMessage(numberOfAccountsOpened);
		} else {
			bankView.showAccountAlreadyExistsMessage();
		}
	}

	private void registerNewClient(Client client) {
		Long num = (long) clients.size() + 1;

		System.out.print("Insira o nome: ");
		String name = scanner.nextLine();

		if(client instanceof NaturalPerson) {
			System.out.print("Insira o CPF: ");
		}else if(client instanceof JuridicalPerson) {
			System.out.print("Insira o CNPJ: ");
		}
		String registrationId = scanner.nextLine();

		System.out.print("Crie uma senha: ");
		String password = scanner.nextLine();

		client.setName(name);
		client.setPassword(password);
		client.setRegistrationId(registrationId);

		boolean clientRegisteredSuccessfully = clients.add(client);

		if (clientRegisteredSuccessfully) {
			activateCheckingAccount(client);
			bankView.showClientSuccessfullyRegisteredMessage();
		} else {
			bankView.showClientAlreadyRegisteredMessage();
		}
	}
	private void listClients(){
		if(clients.isEmpty()) {
			System.out.println("Ainda não há clientes cadastrados.");
			System.out.println("Venha ser o nosso primeiro cliente! =)");
			this.loadMainMenu();
		}else {
			for (Client client : clients) {
				System.out.println("Nome: " + client.getName());
				System.out.println("Chave para transfêrencia: " + client.getRegistrationId()); // Aqui pode ser o número da conta
				System.out.println(" ");
			}
			this.loadMainMenu();
		}
	}



}
