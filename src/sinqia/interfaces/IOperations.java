package sinqia.interfaces;

import sinqia.account.Account;
import sinqia.account.InvestmentAccount;

import java.math.BigDecimal;

public interface IOperations {
    void withdraw(BigDecimal amount);
    
    void deposit(BigDecimal amount);
	
    void transfer(Account destination, BigDecimal amount);
    
    void invest(InvestmentAccount account, BigDecimal amount);
}
