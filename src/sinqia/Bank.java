package sinqia;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
		System.out.println("\n====== MENU PRINCIPAL =======");
		System.out.println("Escolha uma opção: ");
		System.out.println("PF - Registrar novo cliente - PF\n"
						 + "PJ - Registrar novo cliente - PJ\n"
						 + "E - Entrar\n"
						 + "L - Listar clientes\n"
						 + "F - Fechar");

		String menu = scanner.nextLine().toUpperCase();
		switch (menu) {
			case "PF":
				registerNewClient(new NaturalPerson());
				break;
			case "PJ":
				registerNewClient(new JuridicalPerson());
				break;
			case "E":
				login();
				break;
			case "L":
				//			listClients();
				break;
			case "F":
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
			System.out.print("Insira o CPF/CNPJ: ");
			String registrationId = scanner.nextLine();
			try{
				Client currentClient = findClient(registrationId);
				System.out.print("Insira a senha: ");
				currentClient.validatePassword(scanner.nextLine());
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

	public void loadClientMenu(Client client) {
		System.out.println("\n====== MENU DO CLIENTE ======");
		System.out.println("Escolha uma opção: ");
		System.out.println("C - Consultar saldo\n"
						 + "G - Abrir conta poupança\n"
						 + "H - Abrir conta investimento\n"
						 + "S - Sacar\n"
						 + "D - Depositar\n"
						 + "T - Transferir\n"
						 + "I - Investir\n"
						 + "X - Deslogar");

		String clientMenu = scanner.nextLine().toUpperCase();
		switch(clientMenu) {
			case "C":
//				checkBalance();
				break;
			case "G":
				activateInvestmentAccount(client);
				break;
			case "H":
				activateSavingsAccount((NaturalPerson) client);
				break;
			case "S":
//				withdraw();
				break;
			case "D":
//				deposit();
				break;
			case "T":
//				transfer();
				break;
			case "I":
//				invest();
				break;
			case "X":
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
		} else {
			bankView.showAccountAlreadyExistsMessage();
		}
	}

	private void activateInvestmentAccount(Client client) {
		if (client.getInvestmentAccount() == null) {
			client.setInvestmentAccount(new InvestmentAccount(numberOfAccountsOpened + 1, client.getInvestmentInterestRate()));
			numberOfAccountsOpened++;
		} else {
			bankView.showAccountAlreadyExistsMessage();
		}
	}

	public void registerNewClient(Client client) {
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

		clients.add(client);
		this.loadMainMenu();
	}










}
