package sinqia;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

import sinqia.account.Account;
import sinqia.account.CheckingAccount;
import sinqia.account.InvestmentAccount;
import sinqia.account.SavingsAccount;
import sinqia.client.Client;
import sinqia.client.JuridicalPerson;
import sinqia.client.NaturalPerson;
import sinqia.exceptions.AccountNotFoundException;
import sinqia.exceptions.BlankFieldException;
import sinqia.exceptions.ClientNotFoundException;
import sinqia.exceptions.InsufficientFundsExceptions;
import sinqia.exceptions.InvalidAmountException;
import sinqia.exceptions.PasswordMismatchException;
import sinqia.exceptions.TransferException;
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

	public void loadMainMenu() throws InterruptedException {
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
				this.loadMainMenu();
				break;
			case "5":
				System.out.println("Até mais!");
				break;
			default:
				System.out.println("Opção inválida.");
				loadMainMenu();
		}
	}

	private void login() throws InterruptedException {
		if (clients.isEmpty()) {
			System.out.println("Ainda não há clientes cadastrados.");
			this.loadMainMenu();
		} else {
			try {
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
		for (Client client : clients) {
			if (client.getRegistrationId().equals(registrationId)) {
				return client;
			}
		}

		throw new ClientNotFoundException();
	}

	private void loadClientMenu(Client client) throws ClientNotFoundException, InterruptedException {
		// MENU DO CLIENTE
		System.out.println();
		System.out.print("=".repeat(18 - client.getName().length() / 2));
		System.out.print(" Olá, " + client.getName() + " ");
		System.out.print("=".repeat(18 - client.getName().length() / 2));
		System.out.print(client.getName().length() % 2 == 0 ? "=" : "");
		System.out.println();
		System.out.println("|             Escolha uma opção:           |");
		System.out.println("|          1 - Consultar saldo             |");
		System.out.println("|          2 - Acessar conta investimento  |");
		System.out.println("|          3 - Acessar conta poupança      |");
		System.out.println("|          4 - Sacar                       |");
		System.out.println("|          5 - Depositar                   |");
		System.out.println("|          6 - Transferir                  |");
		System.out.println("|          7 - Investir                    |");
		System.out.println("|          8 - Deslogar                    |");
		System.out.println("============================================");

		String clientMenu = scanner.nextLine().toUpperCase();
		switch (clientMenu) {
			case "1":
//				checkBalance();
				long accountNumber = client.getCheckingAccount().getAccountNumber();
				BigDecimal balance = client.getCheckingAccount().getBalance();
				bankView.showAccountBalance(accountNumber, balance);
				Thread.sleep(1000);
				loadClientMenu(client);
				break;
			case "2":
				activateInvestmentAccount(client);
				Thread.sleep(1000);
				loadClientMenu(client);
				break;
			case "3":
				activateSavingsAccount((NaturalPerson) client);
				Thread.sleep(1000);
				loadClientMenu(client);
				break;
			case "4":  // withdraw();
				try {
					BigDecimal amount = bankView.getAmountFromUser();
					client.getCheckingAccount().withdraw(amount);
					BigDecimal newBalance = client.getCheckingAccount().getBalance();
					bankView.showSuccessfulWithdrawMessage(newBalance);
				} catch (InsufficientFundsExceptions e) {
					bankView.showInsufficientFundsMessage();
				} catch (InputMismatchException e) {
					bankView.showInvalidAmountInputMessage();
				} catch (InvalidAmountException e) {
					bankView.showInvalidAmountMessage();
				}

				Thread.sleep(1000);
				loadClientMenu(client);
				break;
			case "5":  // deposit();
				deposit(client);
				Thread.sleep(1000);
				loadClientMenu(client);
				break;
			case "6"://
				transfer(client);
				Thread.sleep(1000);
				loadClientMenu(client);
				break;
			case "7":
				invest(client);
				Thread.sleep(1000);
				loadClientMenu(client);
				break;
			case "8":
				loadMainMenu();
				break;
			default:
				System.out.println("Opção inválida.");
				loadClientMenu(client);
		}
	}

	private void invest(Client client) {
		try {
			if (client.getInvestmentAccount() == null) {
				throw new AccountNotFoundException();
			}

			BigDecimal amount = bankView.getAmountFromUser();

			client.getCheckingAccount().withdraw(amount);
			client.getInvestmentAccount().addToBalance(amount);
			client.getInvestmentAccount().addToBalance(client.getInvestmentAccount().calculateInterest(amount));

			bankView.showSuccessfulInvestmentMessage(client.getInvestmentAccount().getBalance());
		} catch (InputMismatchException e) {
			System.out.println("Por favor, informe valores com o seguinte formato de exemplo: 6.543,21.");
		} catch (InvalidAmountException e) {
			System.out.println("O valor mínimo é de R$ 0,01.");
		} catch (AccountNotFoundException e) {
			bankView.showInvestmentAccountDoesNotExistMessage();
		} catch (InsufficientFundsExceptions e) {
			bankView.showInsufficientFundsMessage();
		}
	}

	private void deposit(Client client) {
		try {
			BigDecimal amountDeposit = bankView.getAmountFromUser();
			client.getCheckingAccount().deposit(amountDeposit);
			BigDecimal newBalance = client.getCheckingAccount().getBalance();
			bankView.showSuccessfulDepositMessage(newBalance);
		} catch (InputMismatchException e) {
			System.out.println("Por favor, informe valores com o seguinte formato de exemplo: 6.543,21.");
		} catch (InvalidAmountException e) {
			System.out.println("O valor mínimo é de R$ 0,01.");
		}
	}

	private void transfer(Client client) throws TransferException, InsufficientFundsExceptions {
		long destinyAccount = bankView.transferScreenAccount();

//		Client clientDestinyTransfer = findClient(String.valueOf(destinyAccount));
//		if (client.getCheckingAccount().getAccountNumber() == clientDestinyTransfer.getCheckingAccount().getAccountNumber()) {
//			throw new TransferException();
//		}

		try {
			Account account = findAccountByAccountNumber(destinyAccount);
			BigDecimal amountTransfer = bankView.transferScreenAmount();

			if (client.getCheckingAccount().getBalance().compareTo(amountTransfer) < 0) {
				throw new InsufficientFundsExceptions();
			}

			client.getCheckingAccount().setBalance(client.getCheckingAccount().getBalance().subtract(amountTransfer));
			account.setBalance(account.getBalance().add(amountTransfer));
			bankView.showAccountBalance(destinyAccount, account.getBalance());
		} catch (AccountNotFoundException e) {
			bankView.showAccountNotFoundMessage();
		} catch (InputMismatchException e) {
			bankView.showInvalidInputForAccountMessage();
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

	private void activateInvestmentAccount(Client client) throws ClientNotFoundException, InterruptedException {
		if (client.getInvestmentAccount() == null) {
			int option = bankView.showInvestmentAccountDoesNotExistMessage();
			if(option == 1) {
				client.setInvestmentAccount(new InvestmentAccount(numberOfAccountsOpened + 1, client.getInvestmentInterestRate()));
				numberOfAccountsOpened++;
				bankView.showAccountSuccessfullyActivatedMessage(numberOfAccountsOpened);
			} else if (option == 2) {
				return;
			} else {
				System.out.println("Comando incorreto.");
				activateInvestmentAccount(client);
			}
		} else {
			loadInvestmentAccountMenu(client);
		}
	}
	
	private void loadInvestmentAccountMenu(Client client) throws ClientNotFoundException, InterruptedException{
		int option = bankView.showInvestmentAccountMenu(client);
		long accountNumber = client.getInvestmentAccount().getAccountNumber();
		BigDecimal balance = client.getInvestmentAccount().getBalance();
		switch(option) {
			case 1:
				bankView.showAccountBalance(accountNumber, balance);
				break;
			case 2:
//				redeem(client);
				break;
			case 3:
				loadClientMenu(client);
				break;
			case 4:
				if(client instanceof NaturalPerson) {
					loadCheckingAccountMenu(client);
				}else {
					System.out.println("Comando inválido.");
				}
				break;
			default:
				System.out.println("Comando inválido");
		}
	}

	private void loadCheckingAccountMenu(Client client) {
		// TODO Auto-generated method stub
		
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
		long num = (long) clients.size() + 1;

		try {
			System.out.print("Insira o nome: ");
			String name = bankView.getNotBlankInputFromUser();

			if (client instanceof NaturalPerson) {
				System.out.print("Insira o CPF: ");
			} else if (client instanceof JuridicalPerson) {
				System.out.print("Insira o CNPJ: ");
			}
			String registrationId = bankView.getNotBlankInputFromUser();

			System.out.print("Crie uma senha: ");
			String password = bankView.getNotBlankInputFromUser();

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
		} catch (BlankFieldException e) {
			bankView.showFieldCanNotBeBlankMessage();
		}
	}

	private void listClients() {
		if (clients.isEmpty()) {
			System.out.println("Ainda não há clientes cadastrados.");
			System.out.println("Venha ser o nosso primeiro cliente! =)");
		} else {
			for (Client client : clients) {
				System.out.println("Nome: " + client.getName());
				System.out.println("Chave para transferência: " + client.getRegistrationId()); // Aqui pode ser o número da conta
				System.out.println(" ");
			}
		}
	}

	private Account findAccountByAccountNumber(long accountNumber) {
		for (Client client : clients) {
			Account accountFound = Arrays.stream(client.getAccounts()).
					                      filter(Objects::nonNull).
					                      filter(account -> account.getAccountNumber() == accountNumber).
					                      findAny().
					                      orElse(null);

			if (accountFound != null) {
				return accountFound;
			}
		}

		throw new AccountNotFoundException();
	}
}
