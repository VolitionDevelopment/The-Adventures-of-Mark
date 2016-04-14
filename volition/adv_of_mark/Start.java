package volition.adv_of_mark;

import volition.adv_of_mark.state.StateManager;
import volition.adv_of_mark.state.menu.impl.DeathMenu;
import volition.adv_of_mark.state.menu.impl.MainMenu;
import volition.adv_of_mark.util.FontManager;
import volition.adv_of_mark.util.GameManager;

/**
 * Created by Demerzel on 2/3/16.
 */
public class Start {
    public static void main(String[] args) {

        new FontManager().registerFont("/volition/adv_of_mark/assets/font/DTM-Sans.otf");
        new FontManager().registerFont("/volition/adv_of_mark/assets/font/DTM-Mono.otf");

        //StateManager.setCurrentState(new MainMenu());
        StateManager.setCurrentState(GameManager.getInstance().getGameState());

        Main gameMain = Main.getInstance();

        //noinspection InfiniteLoopStatement
        while (true)
            gameMain.loop();

    }
}
