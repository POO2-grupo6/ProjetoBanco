package sinqia;

import sinqia.client.Client;
import sinqia.interfaces.IRepository;
import sinqia.repository.Repository;
import sinqia.view.BankView;

import java.util.Locale;

public class Aplicacao {
	public static void main(String[] args) throws InterruptedException {
		Locale.setDefault(new Locale("pt", "BR"));
		IRepository<Client> repository = new Repository<>();
		BankView bankView = new BankView();

		Bank bank = new Bank(repository, bankView);
		bank.loadMainMenu();
	}
}
