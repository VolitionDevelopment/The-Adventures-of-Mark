package me.volition;

import me.volition.state.StateManager;
import me.volition.state.menu.MainMenu;

/**
 * Created by Demerzel on 2/3/16.
 */
public class Start {
    public static void main(String[] args) {
        StateManager manager = StateManager.getInstance();

        manager.setCurrentState(MainMenu.class);
        new Main();
    }
}
