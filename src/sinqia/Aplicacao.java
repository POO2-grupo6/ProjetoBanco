package sinqia;

import java.util.Locale;

public class Aplicacao {

	public static void main(String[] args) {

		Locale.setDefault(new Locale("pt", "BR"));
		
		Bank bank = new Bank();
		
		bank.loadMainMenu();
		
	}
	
}
