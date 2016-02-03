package me.volition.state;

import me.volition.state.game.GameState;
import me.volition.state.menu.HelpMenu;
import me.volition.state.menu.MainMenu;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class StateManager {

    public static final int MAIN_MENU_INDEX = 0, HELP_MENU_INDEX = 1, GAME_INDEX = 2;

    private static State currentState;

    public static void setCurrentState(int index){
        if (index == MAIN_MENU_INDEX)
            currentState = new MainMenu();
        else if (index == HELP_MENU_INDEX)
            currentState = new HelpMenu();
        else if (index == GAME_INDEX)
            currentState = new GameState();

        currentState.init();
    }

    public static State getCurrentState(){
        return currentState;
    }

}
