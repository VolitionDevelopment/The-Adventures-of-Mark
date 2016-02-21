package me.volition;

import me.volition.state.StateManager;
import me.volition.util.FontManager;
import me.volition.util.GameManager;

/**
 * Created by Demerzel on 2/3/16.
 */
public class Start {
    public static void main(String[] args) {

        new FontManager().registerFont("/me/volition/assets/font/DTM-Sans.otf");
        new FontManager().registerFont("/me/volition/assets/font/DTM-Mono.otf");

        //StateManager.setCurrentState(new MainMenu());
        StateManager.setCurrentState(GameManager.getInstance().getGameState());

        Main gameMain = Main.getInstance();

        //noinspection InfiniteLoopStatement
        while (true)
            gameMain.loop();

    }
}
