package accounts;

import java.math.BigDecimal;
import java.util.Objects;

public abstract class Account {

	private String accountNumber;
	private BigDecimal balance;
	
	public Account() {
	}

	public Account(String accountNumber) {
		this.accountNumber = accountNumber;
		this.balance = BigDecimal.ZERO;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	@Override
	public String toString() {
		return "Account [accountNumber=" + accountNumber + ", balance=" + balance + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(accountNumber);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Account other = (Account) obj;
		return Objects.equals(accountNumber, other.accountNumber);
	}
	
	
}
