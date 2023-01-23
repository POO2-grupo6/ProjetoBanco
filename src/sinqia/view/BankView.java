package sinqia.view;

public class BankView {
    public void showAccountAlreadyExistsMessage() {
        System.out.println("A conta já existe.");
    }

    public void showClientAlreadyRegisteredMessage() {
        System.out.println("Já há um cliente registrado com este CPF/CNPJ.");
    }

    public void showClientSuccessfullyRegisteredMessage() {
        System.out.println("Cliente registrado com sucesso!");
    }
}
