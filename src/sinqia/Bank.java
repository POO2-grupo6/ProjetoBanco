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
		Thread.sleep(1000);
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
		System.out.println("|          7 - Deslogar                    |");
		System.out.println("============================================");

		String clientMenu = scanner.nextLine().toUpperCase();
		switch (clientMenu) {
			case "1":  // checkBalance();
				long accountNumber = client.getCheckingAccount().getAccountNumber();
				BigDecimal balance = client.getCheckingAccount().getBalance();
				bankView.showAccountBalance(accountNumber, balance);
				loadClientMenu(client);
				break;
			case "2":
				accessInvestmentAccount(client);
				// loadClientMenu(client);
				break;
			case "3":
				// accessSavingsAccount((NaturalPerson) client);  //tem que fazer
				// loadClientMenu(client);
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

				loadClientMenu(client);
				break;
			case "5":  // deposit();
				manageDeposit(client);
				loadClientMenu(client);
				break;
			case "6":
				manageTransfer(client);
				loadClientMenu(client);
				break;
			case "7":
				loadMainMenu();
				break;
			default:
				bankView.showInvalidOptionMessage();
				loadClientMenu(client);
		}
	}

	private void manageInvestment(Client client) {
		try {
			BigDecimal amount = bankView.getAmountFromUser();

			client.getCheckingAccount().withdraw(amount);
			client.getInvestmentAccount().addToBalance(amount);
			client.getInvestmentAccount().addToBalance(client.getInvestmentAccount().calculateInterest(amount));

			bankView.showSuccessfulInvestmentMessage(client.getInvestmentAccount().getBalance());
		} catch (InputMismatchException e) {
			bankView.showInvalidAmountInputMessage();
		} catch (InvalidAmountException e) {
			bankView.showInvalidAmountMessage();
		} catch (InsufficientFundsExceptions e) {
			bankView.showInsufficientFundsMessage();
		}
	}
	
	private void redeem(Client client) {
		try {
			BigDecimal amount = bankView.getAmountFromUser();

			client.getInvestmentAccount().redeem(client.getCheckingAccount(), amount);

			bankView.showSuccessfulRedeemMessage(client.getInvestmentAccount().getBalance());
		} catch (InputMismatchException e) {
			System.out.println("Por favor, informe valores com o seguinte formato de exemplo: 6.543,21.");
		} catch (InvalidAmountException e) {
			System.out.println("O valor mínimo é de R$ 0,01.");
		} catch (InsufficientFundsExceptions e) {
			bankView.showInsufficientFundsMessage();
		}
	}

	private void manageDeposit(Client client) {
		try {
			BigDecimal amountDeposit = bankView.getAmountFromUser();
			client.getCheckingAccount().deposit(amountDeposit);
			BigDecimal newBalance = client.getCheckingAccount().getBalance();
			bankView.showSuccessfulDepositMessage(newBalance);
		} catch (InputMismatchException e) {
			bankView.showInvalidAmountInputMessage();
		} catch (InvalidAmountException e) {
			bankView.showInvalidAmountMessage();
		}
	}

	private void manageTransfer(Client client) throws TransferException, InsufficientFundsExceptions {
	
//		Client clientDestinyTransfer = findClient(String.valueOf(destinyAccount));
//		if (client.getCheckingAccount().getAccountNumber() == clientDestinyTransfer.getCheckingAccount().getAccountNumber()) {
//			throw new TransferException();
//		}

		try {
			long destinyAccount = bankView.transferScreenAccount();
			
			Account account = findAccountByAccountNumber(destinyAccount);
			BigDecimal amountTransfer = bankView.transferScreenAmount();

			if (client.getCheckingAccount().getBalance().compareTo(amountTransfer) < 0) {
				throw new InsufficientFundsExceptions();
			}

			client.getCheckingAccount().setBalance(client.getCheckingAccount().getBalance().subtract(amountTransfer));
			account.setBalance(account.getBalance().add(amountTransfer));
			bankView.showSuccessfulTransferMessage(currentClient.getCheckingAccount().getBalance());
		} catch (InsufficientFundsExceptions e) {
			bankView.showInsufficientFundsMessage();
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

	private void accessInvestmentAccount(Client client) throws ClientNotFoundException, InterruptedException {
		promptClientToOpenInvestmentAccountIfItDoesNotExist(client);
		if (client.getInvestmentAccount() == null) {
			return;
		}
		loadInvestmentAccountMenu(client);
	}

	private void promptClientToOpenInvestmentAccountIfItDoesNotExist(Client client) {
		if (client.getInvestmentAccount() == null) {
			bankView.promptUserToOpenAccount();

			try {
				int option = bankView.getOptionFromUser();
				if (option == 1) {
					activateInvestmentAccount(client);
				} else if (option != 2) {
					bankView.showInvalidOptionMessage();
				}
			} catch (NumberFormatException e) {
				bankView.showInvalidOptionMessage();
			}
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

	private boolean checkIfInvestmentAccountIsActive(Client client) {
		return client.getInvestmentAccount() != null;
	}
	
	private void loadInvestmentAccountMenu(Client client) throws ClientNotFoundException, InterruptedException {
		long accountNumber = client.getInvestmentAccount().getAccountNumber();
		BigDecimal balance = client.getInvestmentAccount().getBalance();

		int option = bankView.showInvestmentAccountMenu(client);
		switch(option) {
			case 1:
				bankView.showAccountBalance(accountNumber, balance);
				loadInvestmentAccountMenu(client);
				break;
			case 2:
				redeem(client);
				loadInvestmentAccountMenu(client);
				break;
			case 3:
				manageInvestment(client);
				loadInvestmentAccountMenu(client);
				break;
			case 4:
				loadClientMenu(client);
				break;
			case 5:
				if(client instanceof NaturalPerson) {
					loadClientMenu(client);
				} else {
					System.out.println("Comando inválido.");
				}
				break;
			default:
				System.out.println("Comando inválido");
				loadInvestmentAccountMenu(client);
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
			return;
		}

		for (Client client : clients) {
			System.out.println("Nome: " + client.getName());
			System.out.println("Chave para transferência: " + client.getRegistrationId()); // Aqui pode ser o número da conta
			System.out.println("----------");
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
