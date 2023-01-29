package sinqia.view;

import sinqia.client.Client;
import sinqia.client.NaturalPerson;

public class InvestmentAccountMenu {  // extends AccountMenu {
    public void show(Client client) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println();
        System.out.print("=".repeat(18 - client.getName().length() / 2));
        System.out.print(" Olá, " + client.getName() + " ");
        System.out.print("=".repeat(18 - client.getName().length() / 2));
        System.out.print(client.getName().length() % 2 == 0 ? "=" : "");
        System.out.println();
        System.out.println("|             Escolha uma opção:           |");
        System.out.println("|          1 - Consultar saldo             |");
        System.out.println("|          2 - Resgatar                    |");
        System.out.println("|          3 - Investir                    |");
        System.out.println("|          4 - Acessar conta corrente      |");
        System.out.println("|          5 - Deslogar                    |");
        if(client instanceof NaturalPerson) {
            System.out.println("|          6 - Acessar conta poupança      |");
        }
        System.out.println("============================================");
    }
}
