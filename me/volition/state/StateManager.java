package me.volition.state;


/**
 * Created by mccloskeybr on 2/3/16.
 */
public class StateManager {

    public static final int MAIN_MENU_INDEX = 0, HELP_MENU_INDEX = 1, ABOUT_INDEX = 2, GAME_INDEX = 3,
            OPENING_CUTSCENE_INDEX = 4;

    private static State currentState;

    public static void setCurrentState(State state){
        currentState = state;
        currentState.init();
    }

    public static State getCurrentState(){
        return currentState;
    }

}
