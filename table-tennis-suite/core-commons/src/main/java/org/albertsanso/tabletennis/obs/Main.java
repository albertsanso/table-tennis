package org.albertsanso.tabletennis.obs;

public class Main {

    public static void main(String[] args) throws Throwable {
        Screen screen = new Screen();
        DataStore dataStore = new DataStore();
        // register observer
        dataStore.addObserver(screen);

        dataStore.setData("Hello");

        dataStore.notifyObservers();

        dataStore.setData("Hello1");

        dataStore.setData("Hello1");

        dataStore.notifyObservers();

        dataStore.setData("Hello1");
    }

}
