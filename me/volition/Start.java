package me.volition;

import me.volition.state.StateManager;
import me.volition.util.FontManager;

/**
 * Created by Demerzel on 2/3/16.
 */
public class Start {
    public static void main(String[] args) {

        new FontManager().registerFont("/me/volition/assets/font/DTM-Sans.otf");
        new FontManager().registerFont("/me/volition/assets/font/DTM-Mono.otf");
        StateManager.setCurrentState(StateManager.MAIN_MENU_INDEX);
        new Main();

    }
}
