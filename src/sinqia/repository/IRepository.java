package sinqia.repository;

import sinqia.account.Account;
import sinqia.client.Client;
import sinqia.exceptions.ClientNotFoundException;

public interface IRepository {
    boolean save(Client client);

    Client findClient(String registrationId) throws ClientNotFoundException;

    Account findAccountByAccountNumber(long accountNumber);

    void listClients();

    boolean isEmpty();
}
