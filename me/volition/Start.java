package me.volition;

import me.volition.state.StateManager;
import me.volition.state.menu.MainMenu;
import me.volition.util.FontManager;

/**
 * Created by Demerzel on 2/3/16.
 */
public class Start {
    public static void main(String[] args) {

        new FontManager().registerFont("/me/volition/assets/font/DTM-Sans.otf");
        StateManager.setCurrentState(StateManager.MAIN_MENU_INDEX);
        new Main();
    }
}
