package main.java.view;

import main.java.model.Client;

public interface Viewable {
    void showGreetingsMessage();
    void showStartMenu();
    void showLoggedMenu(Client client);
    void showFarewellMessage();
    int getOptionFromUser();
}
