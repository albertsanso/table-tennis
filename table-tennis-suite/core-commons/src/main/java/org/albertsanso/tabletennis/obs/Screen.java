package org.albertsanso.tabletennis.obs;

import java.util.Observable;
import java.util.Observer;

public class Screen implements Observer {
    @Override
    public void update(Observable observable, Object o) {
        // act on the update
        System.out.println("Update");
    }
}
