package sinqia.account;

import java.math.BigDecimal;

public interface IOperations {
	
    public void withdraw(BigDecimal amount);
    
    public void deposit(BigDecimal amount);
	
    public void transfer(Account destination, BigDecimal amount);
    
    public void invest(BigDecimal amount);
    

    
}
