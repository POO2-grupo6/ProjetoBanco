package sinqia.client;

import java.math.BigDecimal;

import sinqia.account.Account;
import sinqia.enums.EInterestRates;
import sinqia.enums.EOperationTaxes;

public class JuridicalPerson extends Client {
    private String cnpj;
    private Account[] accounts = new Account[2];
//    private static final BigDecimal INVESTMENT_INTEREST_RATE = BigDecimal.valueOf(0.035);

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
        return EInterestRates.INVESTMENT_INTEREST_RATE_PJ.getRate();
    }
    
    public BigDecimal getJuridicalPersonWithdrawTax() {
        return EOperationTaxes.WITHDRAW_TAX_RATE_PJ.getTax();
    }
    
    public BigDecimal getJuridicalPersonTransferTax() {
        return EOperationTaxes.TRANSFER_TAX_RATE_PJ.getTax();
    }





}