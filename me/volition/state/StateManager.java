package me.volition.state;

import me.volition.state.cutscene.OpeningCutscene;
import me.volition.state.menu.impl.AboutMenu;
import me.volition.state.menu.impl.HelpMenu;
import me.volition.state.menu.impl.MainMenu;
import me.volition.util.GameManager;


/**
 * Created by mccloskeybr on 2/3/16.
 */
public class StateManager {

    public static final int MAIN_MENU_INDEX = 0, HELP_MENU_INDEX = 1, ABOUT_INDEX = 2, GAME_INDEX = 3,
            OPENING_CUTSCENE_INDEX = 4;

    private static State currentState;

    public static void setCurrentState(int state){
        if (state == MAIN_MENU_INDEX)
            currentState = new MainMenu();
        else if (state == HELP_MENU_INDEX)
            currentState = new HelpMenu();
        else if(state == ABOUT_INDEX)
            currentState = new AboutMenu();
        else if (state == GAME_INDEX)
            currentState = GameManager.newGame();
        else if (state == OPENING_CUTSCENE_INDEX)
            currentState = new OpeningCutscene();

        currentState.init();
    }

    public static void setCurrentState(State state){
        currentState = state;
    }

    public static State getCurrentState(){
        return currentState;
    }

}
