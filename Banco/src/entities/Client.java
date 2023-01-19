package entities;

import java.util.Objects;
import java.util.Set;

import accounts.Account;
import accounts.CheckingAccount;
import accounts.InvestmentAccount;

public abstract class Client {

	private Long id;
	private String name;
	private String password;
	protected Set<? extends Account> accounts;
	
	public Client() {
	}

	public Client(Long id, String name, String password) {
		this.id = id;
		this.name = name;
		this.password = password;
		CheckingAccount ca = new CheckingAccount();
		InvestmentAccount ia = new InvestmentAccount();
		this.accounts.add(ca);
		this.accounts.add(ia);
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	@Override
	public String toString() {
		return "Client [id=" + id + ", name=" + name + ", password=" + password + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		return Objects.equals(id, other.id);
	}
	
	
}
