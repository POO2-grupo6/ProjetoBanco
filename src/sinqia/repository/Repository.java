package sinqia.repository;

import sinqia.account.Account;
import sinqia.client.Client;
import sinqia.client.NaturalPerson;
import sinqia.exceptions.AccountNotFoundException;
import sinqia.exceptions.ClientNotFoundException;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class Repository implements IRepository {
    private Set<Client> clients = new HashSet<>();

    @Override
    public boolean save(Client client) {
        return clients.add(client);
    }

    @Override
    public Client findClient(String registrationId) throws ClientNotFoundException {
        for (Client client : clients) {
            if (client.getRegistrationId().equals(registrationId)) {
                return client;
            }
        }

        throw new ClientNotFoundException();
    }

    @Override
    public void listClients() {
        if (clients.isEmpty()) {
            System.out.println("Ainda não há clientes cadastrados.");
            System.out.println("Venha ser o nosso primeiro cliente! =)");
            return;
        }

        for (Client client : clients) {
            System.out.println("--------------------");
            System.out.println("Nome: " + client.getName());
            System.out.println("Número da Conta Corrente para transferência: " + client.getCheckingAccount().getAccountNumber()); // Aqui pode ser o número da conta
            if (client instanceof NaturalPerson && ((NaturalPerson) client).getSavingsAccount() != null){
                System.out.println("Numero da Conta Poupança para transferência: " + ((NaturalPerson) client).getSavingsAccount().getAccountNumber());
            }
        }
    }

    @Override
    public Account findAccountByAccountNumber(long accountNumber) {
        for (Client client : clients) {
            Account accountFound = Arrays.stream(client.getAccounts()).
                    filter(Objects::nonNull).
                    filter(account -> account.getAccountNumber() == accountNumber).
                    findAny().
                    orElse(null);

            if (accountFound != null) {
                return accountFound;
            }
        }

        throw new AccountNotFoundException();
    }

    @Override
    public boolean isEmpty() {
        return clients.isEmpty();
    }
}
