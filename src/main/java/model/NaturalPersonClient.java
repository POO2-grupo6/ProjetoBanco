package main.java.model;

public class NaturalPersonClient extends Client {
    String cpf;
    Account savingsAccount = new SavingsAccount();

    public Client(String name, String cpf, String password) {
        this.name = name;
        this.cpf = cpf;
        this.password = password;
    }

    protected NaturalPersonClient(String name, String password) {
        super(name, password);
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    boolean passwordIsEqualTo(String string) {
        return this.password.equals(string);
    }
}
