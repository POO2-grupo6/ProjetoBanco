package sinqia.client;

import java.math.BigDecimal;
import java.util.Objects;

import sinqia.account.Account;
import sinqia.account.CheckingAccount;
import sinqia.account.InvestmentAccount;
import sinqia.exceptions.PasswordMismatchException;

public abstract class Client {
	private String name;
	private String password;
	private String registrationId;

	protected Client() {
	}
	
	protected Client(String name) {
		this.name = name;
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
	
	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Client client = (Client) o;
		return Objects.equals(registrationId, client.registrationId);
	}

	@Override
	public int hashCode() {
		return Objects.hash(registrationId);
	}

	public void validatePassword(String loginAttempt) throws PasswordMismatchException {
		if (!this.password.equals(loginAttempt)) {
			throw new PasswordMismatchException();
		}
	}

    public abstract Account[] getAccounts();

	public abstract BigDecimal getInvestmentInterestRate();

	public CheckingAccount getCheckingAccount() {
		return (CheckingAccount) getAccounts()[0];
	}

	public InvestmentAccount getInvestmentAccount() {
		return (InvestmentAccount) getAccounts()[1];
	}

	public void setInvestmentAccount(InvestmentAccount investmentAccount) {
		getAccounts()[1] = investmentAccount;
	}

	public void setCheckingAccount(CheckingAccount checkingAccount) {
		getAccounts()[0] = checkingAccount;
	}
}
