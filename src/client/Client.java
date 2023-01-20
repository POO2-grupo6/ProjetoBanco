package client;

import java.util.ArrayList;
import java.util.List;

import account.Account;

public class Client {
	
	String name;
	List<Account> accounts = new ArrayList<>();

	public Client(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}

}
