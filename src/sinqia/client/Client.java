package sinqia.client;

import java.math.BigDecimal;
import java.util.Objects;

import sinqia.account.Account;
import sinqia.account.CheckingAccount;
import sinqia.account.InvestmentAccount;
import sinqia.account.SavingsAccount;
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

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public abstract Account[] getAccounts();

	public abstract BigDecimal getInvestmentInterestRate();

	public CheckingAccount getCheckingAccount() {
		return (CheckingAccount) getAccounts()[0];
	}

	public void setCheckingAccount(CheckingAccount checkingAccount) {
		getAccounts()[0] = checkingAccount;
	}

	public InvestmentAccount getInvestmentAccount() {
		return (InvestmentAccount) getAccounts()[1];
	}

	public void setInvestmentAccount(InvestmentAccount investmentAccount) {
		getAccounts()[1] = investmentAccount;
	}

	public SavingsAccount getSavingsAccount (){
		return (SavingsAccount) getAccounts()[2];
	}

	public void setSavingsAccount (SavingsAccount savingsAccount){
		getAccounts()[2] = savingsAccount;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void validatePassword(String loginAttempt) throws PasswordMismatchException {
		if (!this.password.equals(loginAttempt)) {
			throw new PasswordMismatchException();
		}
	}

	public void withdraw(Account account, BigDecimal amountWithdraw) {
		account.removeFromBalance(amountWithdraw);
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
}
