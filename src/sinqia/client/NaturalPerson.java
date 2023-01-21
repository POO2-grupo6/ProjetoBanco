package sinqia.client;

import java.math.BigDecimal;

import sinqia.account.Account;

public class NaturalPerson extends Client {

	private String cpf;
	private Account[] accounts = new Account[3];
	static final BigDecimal SAVING_INTEREST_RATE = BigDecimal.valueOf(0.01);
	static final BigDecimal INVESTMENT_INTEREST_RATE = BigDecimal.valueOf(0.015);
	
    public NaturalPerson() {
    }
	
    public NaturalPerson(String name, String cpf) {
        super(name);
        this.cpf = cpf;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
    
	public Account[] getAccounts() {
		return accounts;
	}

	public void setAccounts(Account[] accounts) {
		this.accounts = accounts;
	}

	public static BigDecimal getSavingInterestRate() {
		return SAVING_INTEREST_RATE;
	}

	public static BigDecimal getInvestmentInterestRate() {
		return INVESTMENT_INTEREST_RATE;
	}
	
	
	
}
