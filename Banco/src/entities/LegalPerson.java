package entities;

import java.util.HashSet;
import java.util.Set;

import accounts.InvestmentAccount;

public class LegalPerson extends Client {

	private String cnpj;
	
	public LegalPerson() {
	}

	public LegalPerson(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
	
}
