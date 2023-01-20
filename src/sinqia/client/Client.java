package sinqia.client;

import sinqia.account.Account;

public class Client {
	private String name;
	private Account[] accounts = new Account[2];

	public Client(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Account[] getAccounts() {
		return accounts;
	}

	public void setAccounts(Account[] accounts) {
		this.accounts = accounts;
	}
}
