package sinqia.client;

import java.math.BigDecimal;
import java.util.Objects;

import sinqia.account.Account;
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
	public int hashCode() {
		return Objects.hash(registrationId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		return Objects.equals(this.registrationId, obj);  // Acho que tem algum problema aqui (Marcos)
	}

	public boolean passwordIsEqualTo(String loginAttempt) throws PasswordMismatchException{
		if (this.password.equals(loginAttempt))
			return true;
		else
			throw new PasswordMismatchException();
	}

    public abstract Account[] getAccounts();

	public abstract BigDecimal getInvestmentInterestRate();
}
