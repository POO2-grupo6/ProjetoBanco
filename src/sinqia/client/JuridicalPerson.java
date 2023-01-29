package sinqia.client;

import java.math.BigDecimal;

import sinqia.account.Account;
import sinqia.enums.EInterestRates;
import sinqia.enums.EOperationTaxes;

public class JuridicalPerson extends Client {
    private Account[] accounts = new Account[2];

    public JuridicalPerson(){
        super();
    }  // precisa disso? (by: Marcos)

    public JuridicalPerson(String name){
        super(name);
    }

    public Account[] getAccounts() {
        return accounts;
    }

    @Override
    public BigDecimal getInvestmentInterestRate() {
        return EInterestRates.INVESTMENT_INTEREST_RATE_PJ.getRate();
    }

    @Override
    public void withdraw(Account account, BigDecimal amount) {
        BigDecimal valueToDebit = amount.multiply(BigDecimal.ONE.add(EOperationTaxes.WITHDRAW_TAX_RATE_PJ.getTax()));
        account.removeFromBalance(valueToDebit);
    }

    @Override
    public void transfer(Account originAccount, Account destinationAccount, BigDecimal amount) {
        BigDecimal amountToDebit = amount.multiply(BigDecimal.ONE.add(EOperationTaxes.TRANSFER_TAX_RATE_PJ.getTax()));

        originAccount.removeFromBalance(amountToDebit);
        destinationAccount.addToBalance(amount);
    }
}
