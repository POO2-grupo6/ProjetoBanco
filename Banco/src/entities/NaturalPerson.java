package entities;

import java.util.Set;

import accounts.InvestmentAccount;
import accounts.SavingAccount;

public class NaturalPerson extends Client {

	private String cpf;
	
	public NaturalPerson() {
	}

	public NaturalPerson(String cpf) {
		this.cpf = cpf;
		SavingAccount sa = new SavingAccount();
		this.accounts.add(sa);
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
}
