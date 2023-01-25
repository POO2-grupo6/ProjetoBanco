package sinqia;

import sinqia.view.BankView;

import java.util.Locale;

public class Aplicacao {
	public static void main(String[] args) throws InterruptedException {
		Locale.setDefault(new Locale("pt", "BR"));
		BankView bankView = new BankView();
		Bank bank = new Bank(bankView);
		bank.loadMainMenu();
	}
}
