package sinqia.interfaces;

import sinqia.account.SavingsAccount;

public interface ICanOpenSavingsAccount {
    SavingsAccount getSavingsAccount();

    void setSavingsAccount(SavingsAccount savingsAccount);
}
