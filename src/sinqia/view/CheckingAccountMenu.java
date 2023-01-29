package sinqia.view;

public class CheckingAccountMenu extends AccountMenu {
    public void show(String name) throws InterruptedException {
        Thread.sleep(1000);
        System.out.println();
        System.out.print("=".repeat(18 - name.length() / 2));
        System.out.print(" Olá, " + name + " ");
        System.out.print("=".repeat(18 - name.length() / 2));
        System.out.print(name.length() % 2 == 0 ? "=" : "");
        System.out.println();
        System.out.println("|             Escolha uma opção:           |");
        System.out.println("|          1 - Consultar saldo             |");
        System.out.println("|          2 - Acessar conta investimento  |");
        System.out.println("|          3 - Acessar conta poupança      |");
        System.out.println("|          4 - Sacar                       |");
        System.out.println("|          5 - Depositar                   |");
        System.out.println("|          6 - Transferir                  |");
        System.out.println("|          7 - Deslogar                    |");
        System.out.println("============================================");
    }
}
