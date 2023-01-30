package sinqia.interfaces;

import sinqia.account.Account;
import sinqia.exceptions.ClientNotFoundException;

public interface IRepository<T> {
    boolean save(T client);

    T findClient(String registrationId) throws ClientNotFoundException;

    Account findAccountByAccountNumber(long accountNumber);

    void listClients();

    boolean isEmpty();
}
