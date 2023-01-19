package accounts;

public abstract class AccountThatPaysInterest extends Account {

	private Double interestRate;

	public AccountThatPaysInterest() {
	}

	public AccountThatPaysInterest(Double interestRate) {
		this.interestRate = interestRate;
	}

	public Double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(Double interestRate) {
		this.interestRate = interestRate;
	}
	
	public void invest() {
		
	}
	
	
}
