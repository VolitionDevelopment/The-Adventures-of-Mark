package me.volition.state;


/**
 * Created by mccloskeybr on 2/3/16.
 */
public class StateManager {

    private static State currentState;

    public static void setCurrentState(State state){
        currentState = state;
        currentState.init();
    }

    public static State getCurrentState(){
        return currentState;
    }

}
