package sinqia.client;

import java.math.BigDecimal;

import sinqia.account.Account;

public class JuridicalPerson extends Client {
    private String cnpj;
	private Account[] accounts = new Account[2];
	private static final BigDecimal INVESTMENT_INTEREST_RATE = BigDecimal.valueOf(0.035);
    
	public JuridicalPerson(){
        super();
    }
	
    public JuridicalPerson(String name, String cnpj){
        super(name);
        this.cnpj = cnpj;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
    
	public Account[] getAccounts() {
		return accounts;
	}

	public void setAccounts(Account[] accounts) {
		this.accounts = accounts;
	}

    @Override
	public BigDecimal getInvestmentInterestRate() {
		return INVESTMENT_INTEREST_RATE;
	}





}
