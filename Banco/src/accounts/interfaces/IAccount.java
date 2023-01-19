package accounts.interfaces;

import java.math.BigDecimal;

import accounts.Account;

public abstract interface IAccount {
	
	public static void transfer(Account origin, Account destination, BigDecimal amount) {
		hasEnoughBalance(origin);
	}
	
	public static Boolean hasEnoughBalance(Account account) {
		return null;
		
	}

	
}
