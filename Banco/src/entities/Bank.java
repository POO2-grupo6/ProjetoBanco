package entities;

import java.util.Set;

public class Bank {

	public Set<Client> clients;

	public Bank() {
	}

	public Set<Client> getClients() {
		return clients;
	}
	
	public void registerNewClient() {
		NaturalPerson client = new NaturalPerson();
		Long num = (long) clients.size() + 1; 
		client.setId(num);
		clients.add(client);

	
	}
	
	
}
