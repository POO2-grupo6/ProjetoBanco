package sinqia;

import java.math.BigDecimal;
import java.util.InputMismatchException;
import java.util.List;

import sinqia.account.Account;
import sinqia.account.CheckingAccount;
import sinqia.interfaces.IAcceptsTransfer;
import sinqia.interfaces.ICanOpenSavingsAccount;
import sinqia.interfaces.IPaysInterest;
import sinqia.account.InvestmentAccount;
import sinqia.account.SavingsAccount;
import sinqia.client.Client;
import sinqia.client.JuridicalPerson;
import sinqia.client.NaturalPerson;
import sinqia.exceptions.AccountDoesNotAcceptTransferException;
import sinqia.exceptions.AccountNotFoundException;
import sinqia.exceptions.BlankFieldException;
import sinqia.exceptions.ClientNotFoundException;
import sinqia.exceptions.InsufficientFundsExceptions;
import sinqia.exceptions.InvalidAmountException;
import sinqia.exceptions.PasswordMismatchException;
import sinqia.exceptions.TransferException;
import sinqia.interfaces.IRepository;
import sinqia.view.BankView;

public class Bank {
	private BankView bankView;
	private IRepository repository;
	private Client currentClient;
	private long numberOfAccountsOpened = 0;

	public Bank(IRepository repository, BankView bankView) {
		this.repository = repository;
		this.bankView = bankView;
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
				repository.listClients();
				this.loadMainMenu();
				break;
			case "5":
				bankView.showFarewellMessage();
				break;
			default:
				bankView.showInvalidOptionMessage();
				loadMainMenu();
		}
	}

	private void login() throws InterruptedException {
		if (repository.isEmpty()) {
			bankView.showNoClientsRegisteredYetMessage();
			this.loadMainMenu();
		} else {
			try {
				List<String> loginCredentials = bankView.getClientCredentials();
				Client client = repository.findClient(loginCredentials.get(0));
				client.validatePassword(loginCredentials.get(1));

				if (client instanceof ICanOpenSavingsAccount) {
					this.loadCheckingAccountMenuWithSavingsAccount(client);
				} else {
					this.loadCheckingAccountMenuWithoutSavingsAccount(client);
				}
			} catch (ClientNotFoundException | PasswordMismatchException e) {
				bankView.showExceptionMessage(e.getMessage());
				this.loadMainMenu();
			}
		}
	}

	private void manageDeposit(Account account) {
		try {
			BigDecimal amount = bankView.getAmountFromUser();
			account.addToBalance(amount);

			if (account instanceof IPaysInterest) {
				account.addToBalance(((IPaysInterest) account).calculateInterest(amount));
			}

			BigDecimal newBalance = account.getBalance();
			bankView.showSuccessfulOperationMessage(newBalance);
		} catch (InputMismatchException e) {
			bankView.showInvalidAmountInputMessage();
		} catch (InvalidAmountException e) {
			bankView.showInvalidAmountMessage();
		}
	}

	private void manageTransfer(Client client, Account originAccount) throws TransferException, InsufficientFundsExceptions {
		try {
			long destinationAccountNumber = bankView.getDestinationAccountNumberFromUser();
			if (destinationAccountNumber == originAccount.getAccountNumber()) {
				throw new TransferException();
			}

			Account destinationAccount = repository.findAccountByAccountNumber(destinationAccountNumber);
			if (!(destinationAccount instanceof IAcceptsTransfer)) {
				throw new AccountDoesNotAcceptTransferException();
			}

			BigDecimal amount = bankView.getAmountFromUser();
			if (destinationAccount instanceof IPaysInterest) {
				destinationAccount.addToBalance(((IPaysInterest) destinationAccount).calculateInterest(amount));
			}

			client.transfer(originAccount, destinationAccount, amount);
			bankView.showSuccessfulOperationMessage(originAccount.getBalance());
		} catch (InsufficientFundsExceptions e) {
			bankView.showInsufficientFundsMessage();
		} catch (AccountNotFoundException e) {
			bankView.showAccountNotFoundMessage();
		} catch (InputMismatchException | NumberFormatException e) {
			bankView.showInvalidInputForAccountMessage();
		} catch (TransferException e) {
			bankView.showDestinationAndOriginAccountCanNotBeTheSameMessage();
		} catch (AccountDoesNotAcceptTransferException e) {
			bankView.showAccountDoesNotAcceptTransferMessage();
		}
	}

	private void manageWithdraw(Client client, Account account) throws InsufficientFundsExceptions, InputMismatchException, InvalidAmountException {
//		try {
//			BigDecimal amount = bankView.getAmountFromUser();
//			client.getCheckingAccount().withdraw(amount);
//			BigDecimal newBalance = client.getCheckingAccount().getBalance();
//			bankView.showSuccessfulWithdrawMessage(newBalance);
//		} catch (InsufficientFundsExceptions e) {
//			bankView.showInsufficientFundsMessage();
//		} catch (InputMismatchException e) {
//			bankView.showInvalidAmountInputMessage();
//		} catch (InvalidAmountException e) {
//			bankView.showInvalidAmountMessage();
//		}

		try {
			BigDecimal amount = bankView.getAmountFromUser();
			client.withdraw(account, amount);
			bankView.showSuccessfulOperationMessage(account.getBalance());
		} catch (InsufficientFundsExceptions e) {
			bankView.showInsufficientFundsMessage();
		} catch (InputMismatchException e) {
			bankView.showInvalidInputForAccountMessage();
		} catch (InvalidAmountException e) {
			bankView.showInvalidAmountMessage();
		}
	}

	private void manageInvestment(Client client) {
		try {
			BigDecimal amount = bankView.getAmountFromUser();

			client.getCheckingAccount().withdraw(amount);
			client.getInvestmentAccount().addToBalance(amount);
			client.getInvestmentAccount().addToBalance(client.getInvestmentAccount().calculateInterest(amount));

			bankView.showSuccessfulOperationMessage(client.getInvestmentAccount().getBalance());
		} catch (InputMismatchException e) {
			bankView.showInvalidAmountInputMessage();
		} catch (InvalidAmountException e) {
			bankView.showInvalidAmountMessage();
		} catch (InsufficientFundsExceptions e) {
			bankView.showInsufficientFundsMessage();
		}
	}

	private void manageInvestmentRedemption(Client client) {
		try {
			BigDecimal amount = bankView.getAmountFromUser();
			client.getInvestmentAccount().redeem(client.getCheckingAccount(), amount);
			bankView.showSuccessfulOperationMessage(client.getInvestmentAccount().getBalance());
		} catch (InputMismatchException e) {
			bankView.showInvalidAmountInputMessage();
		} catch (InvalidAmountException e) {
			bankView.showInvalidAmountMessage();
		} catch (InsufficientFundsExceptions e) {
			bankView.showInsufficientFundsMessage();
		}
	}

	private void promptClientToOpenSavingsAccountIfItDoesNotExist(NaturalPerson client) {
		if (client.getSavingsAccount() == null) {
			bankView.promptUserToOpenAccount();

			try {
				int option = bankView.getOptionFromUser();
				if (option == 1) {
					activateSavingsAccount(client);
				} else if (option != 2) {
					bankView.showInvalidOptionMessage();
				}
			} catch (NumberFormatException e) {
				bankView.showInvalidOptionMessage();
			}
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

	private void accessSavingsAccount(NaturalPerson client) throws ClientNotFoundException, InterruptedException {
		promptClientToOpenSavingsAccountIfItDoesNotExist(client);
		if (client.getSavingsAccount() == null) {
			return;
		}
		loadSavingsAccountMenu(client);
	}

	private void loadSavingsAccountMenu(NaturalPerson client) throws ClientNotFoundException, InterruptedException {
		long accountNumber = client.getSavingsAccount().getAccountNumber();
		BigDecimal balance = client.getSavingsAccount().getBalance();

		bankView.showSavingsAccountMenu(client.getName());
		int option = bankView.getOptionFromUser();

		switch (option) {
			case 1:   // ver saldo
				bankView.showAccountBalance(accountNumber, balance);
				loadSavingsAccountMenu(client);
				break;
			case 2:
				accessInvestmentAccount(client);
				if (client.getInvestmentAccount() == null) {
					loadSavingsAccountMenu(client);
				}
				break;
			case 3:
				loadCheckingAccountMenuWithSavingsAccount(client);
				break;
			case 4:  // sacar
				manageWithdraw(client, client.getSavingsAccount());
				loadSavingsAccountMenu(client);
				break;
			case 5:  // depositar
				manageDeposit(client.getSavingsAccount());
				loadSavingsAccountMenu(client);
				break;
			case 6:
				manageTransfer(client, client.getSavingsAccount());
				loadSavingsAccountMenu(client);
				break;
			case 7:
				loadMainMenu();
				break;
			default:
				bankView.showInvalidOptionMessage();
				loadSavingsAccountMenu(client);
		}
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

	private void accessInvestmentAccount(Client client) throws ClientNotFoundException, InterruptedException {
		promptClientToOpenInvestmentAccountIfItDoesNotExist(client);
		if (client.getInvestmentAccount() == null) {
			return;
		}
		loadInvestmentAccountMenu(client);
	}

	private void loadInvestmentAccountMenu(Client client) throws ClientNotFoundException, InterruptedException {
		long accountNumber = client.getInvestmentAccount().getAccountNumber();
		BigDecimal balance = client.getInvestmentAccount().getBalance();

		bankView.showInvestmentAccountMenu(client);
		int option = bankView.getOptionFromUser();

		switch (option) {
			case 1:
				bankView.showAccountBalance(accountNumber, balance);
				loadInvestmentAccountMenu(client);
				break;
			case 2:
				manageInvestmentRedemption(client);
				loadInvestmentAccountMenu(client);
				break;
			case 3:
				manageInvestment(client);
				loadInvestmentAccountMenu(client);
				break;
			case 4:
				if (client instanceof ICanOpenSavingsAccount) {
					loadCheckingAccountMenuWithSavingsAccount(client);
				} else {
					loadCheckingAccountMenuWithoutSavingsAccount(client);
				}
				break;
			case 5:
				loadMainMenu();
				break;
			case 6:
				if(client instanceof NaturalPerson) {
					accessSavingsAccount((NaturalPerson) client);
					if (((NaturalPerson) client).getSavingsAccount() == null) {
						loadInvestmentAccountMenu(client);
					}
				} else {
					bankView.showInvalidOptionMessage();
					loadInvestmentAccountMenu(client);
				}
				break;
			default:
				bankView.showInvalidOptionMessage();
				loadInvestmentAccountMenu(client);
		}
	}

	private boolean checkIfInvestmentAccountIsActive(Client client) {
		return client.getInvestmentAccount() != null;
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

	private void loadCheckingAccountMenuWithSavingsAccount(Client client) throws ClientNotFoundException, InterruptedException {
		long accountNumber = client.getCheckingAccount().getAccountNumber();
		BigDecimal balance = client.getCheckingAccount().getBalance();

		bankView.showCheckingAccountMenuWithSavingsAccount(client.getName());
		int option = bankView.getOptionFromUser();

		switch (option) {
			case 1:  // ver saldo
				bankView.showAccountBalance(accountNumber, balance);
				loadCheckingAccountMenuWithSavingsAccount(client);
				break;
			case 2:
				accessInvestmentAccount(client);
				if (client.getInvestmentAccount() == null) {
					loadCheckingAccountMenuWithSavingsAccount(client);
				}
				break;
			case 3:
//				System.out.println("Ok, indo para Conta Poupança..");  // para desacoplar o programa do frontend, não é legal colocar System.out no programa em si, teria que ficar nas classes que interagem com o usuário
				accessSavingsAccount((NaturalPerson) client);
				if (((NaturalPerson) client).getSavingsAccount() == null) {
					loadCheckingAccountMenuWithSavingsAccount(client);
				}
				break;
			case 4:  // sacar
				manageWithdraw(client, client.getCheckingAccount());
				loadCheckingAccountMenuWithSavingsAccount(client);
				break;
			case 5:  // depositar
				manageDeposit(client.getCheckingAccount());
				loadCheckingAccountMenuWithSavingsAccount(client);
				break;
			case 6:
				manageTransfer(client, client.getCheckingAccount());
				loadCheckingAccountMenuWithSavingsAccount(client);
				break;
			case 7:
				loadMainMenu();
				break;
			default:
				bankView.showInvalidOptionMessage();
				loadCheckingAccountMenuWithSavingsAccount(client);
		}
	}

	private void loadCheckingAccountMenuWithoutSavingsAccount(Client client) throws ClientNotFoundException, InterruptedException {
		long accountNumber = client.getCheckingAccount().getAccountNumber();
		BigDecimal balance = client.getCheckingAccount().getBalance();

		bankView.showCheckingAccountMenuWithoutSavingsAccount(client.getName());
		int option = bankView.getOptionFromUser();

		switch (option) {
			case 1:  // ver saldo
				bankView.showAccountBalance(accountNumber, balance);
				loadCheckingAccountMenuWithoutSavingsAccount(client);
				break;
			case 2:
				accessInvestmentAccount(client);
				if (client.getInvestmentAccount() == null) {
					loadCheckingAccountMenuWithoutSavingsAccount(client);
				}
				break;
			case 3:  // sacar
				manageWithdraw(client, client.getCheckingAccount());
				loadCheckingAccountMenuWithoutSavingsAccount(client);
				break;
			case 4:  // depositar
				manageDeposit(client.getCheckingAccount());
				loadCheckingAccountMenuWithoutSavingsAccount(client);
				break;
			case 5:
				manageTransfer(client, client.getCheckingAccount());
				loadCheckingAccountMenuWithoutSavingsAccount(client);
				break;
			case 6:
				loadMainMenu();
				break;
			default:
				bankView.showInvalidOptionMessage();
				loadCheckingAccountMenuWithoutSavingsAccount(client);
		}
	}

	private void registerNewClient(Client client) {
		try {
			String[] registrationData;

			if (client instanceof NaturalPerson) {
				registrationData = bankView.getNaturalPersonRegistrationData();
			} else if (client instanceof JuridicalPerson) {
				registrationData = bankView.getJuridicalPersonRegistrationData();
			} else {
				throw new RuntimeException();
			}

			client.setName(registrationData[0]);
			client.setRegistrationId(registrationData[1]);
			client.setPassword(registrationData[2]);

			boolean clientRegisteredSuccessfully = repository.save(client);

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
}
