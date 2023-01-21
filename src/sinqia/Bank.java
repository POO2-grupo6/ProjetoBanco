package sinqia;

import java.awt.desktop.PrintFilesEvent;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

import sinqia.client.Client;
import sinqia.client.JuridicalPerson;
import sinqia.client.NaturalPerson;
import sinqia.exceptions.ClientNotFoundException;
import sinqia.exceptions.PasswordMismatchException;

public class Bank {

	Scanner sc = new Scanner(System.in);

	public Set<Client> clients;

	public Bank() {
		clients = new HashSet<Client>();
	}

	public Set<Client> getClients() {
		return clients;
	}

	public void loadMainMenu() {
		System.out.println("\n====== MENU PRINCIPAL =======");
		System.out.println("Escolha uma opção: ");
		System.out.println("F - Registrar novo cliente - PF\n"
				+ "J - Registrar novo cliente - PJ\n"
				+ "E - Entrar\n"
				+ "L - Listar clientes\n"
				+ "F - Fechar");

		String menu = sc.nextLine().toUpperCase();
		switch(menu) {
		case "F":
			registerNewClient(new NaturalPerson());
			break;
		case "J":
			registerNewClient(new JuridicalPerson());
			break;
		case "E":
			login();
			break;
			//			case "L":
			//				listClients();
			//				break;
			//			case "F":
			//				systemShutdown();
			//				break;
		default: 
			System.out.println("Opção inválida.");
		}


	}

	private void login() {
		if(clients.isEmpty()) {
			System.out.println("Ainda não há clientes cadastrados.");
			this.loadMainMenu();
		}else {
			System.out.printf("Insira o CPF/CNPJ: ");
			String registrationId = sc.nextLine();
			try{
				Client currentClient = findClient(registrationId);
				System.out.printf("Insira a senha: ");
				currentClient.passwordIsEqualTo(sc.nextLine());
				this.loadClientMenu(currentClient);
			} catch (ClientNotFoundException e) {
				System.out.println(e.getMessage());
				this.loadMainMenu();
			} catch (PasswordMismatchException e) {
				System.out.println(e.getMessage());
				this.loadMainMenu();
			}
		}

	}

	private Client findClient(String registrationId) throws ClientNotFoundException {
		for(Client client : clients) {
			if(client.equals(registrationId)) {
				return client;
			}
		}
		throw new ClientNotFoundException();
	}

	public void loadClientMenu(Client client) {
		System.out.println("\n====== MENU DO CLIENTE ======");
		System.out.println("Escolha uma opção: ");
		System.out.println("C - Consultar saldo\n"
				+ "G - Abrir Conta Poupança\n"
				+ "H - Abrir Conta Investimento\n"
				+ "S - Sacar\n"
				+ "D - Depositar\n"
				+ "T - Transferir\n"
				+ "I - Investir\n"
				+ "X - Deslogar");

		//		String clientMenu = sc.nextLine().toUpperCase();
		//		switch(clientMenu) {
		//			case "C":
		//				checkBalance();
		//				break;
		//			case "G":
		//				activateInvestmentAccount();
		//				break;
		//			case "H":
		//				activateSavingAccount();
		//				break;
		//			case "S":
		//				withdraw();
		//				break;
		//			case "D":
		//				deposit();	
		//				break;
		//			case "T":
		//				transfer();
		//				break;
		//			case "I":
		//				invest();
		//				break;
		//			case "X":
		//				logout();
		//				break;
		//			default: 
		//				System.out.println("Opção inválida.");
		//		}
	}

	public void registerNewClient(Client client) {
		Long num = (long) clients.size() + 1;
		System.out.printf("Insira o nome: ");
		String name = sc.nextLine();
		if(client.getClass()==NaturalPerson.class) {
			System.out.printf("Insira o CPF: ");
		}else if(client.getClass()==JuridicalPerson.class) {
			System.out.printf("Insira o CNPJ: ");
		}
		String registrationId = sc.nextLine();
		System.out.printf("Crie uma senha: ");
		String password = sc.nextLine();
		client.setName(name);
		client.setPassword(password);
		client.setRegistrationId(registrationId);
		clients.add(client);
		this.loadMainMenu();
	}










}
