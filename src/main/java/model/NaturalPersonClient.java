package main.java.model;

public class NaturalPersonClient extends Client {
    Account savingsAccount;

    public NaturalPersonClient(String name, String registrationId, String password) {
        super(name, registrationId, password);
    }
}
