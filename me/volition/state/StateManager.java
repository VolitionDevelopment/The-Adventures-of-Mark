package me.volition.state;

import me.volition.state.game.GameState;
import me.volition.state.menu.HelpMenu;
import me.volition.state.menu.MainMenu;

import java.util.HashMap;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class StateManager {
    private static StateManager stateManager;
    private HashMap<Integer, State> stateHashMap;
    private State currentState;

    private StateManager(){
        stateHashMap = new HashMap<>();


        new HelpMenu().register(this);
        new MainMenu().register(this);
        new GameState().register(this);

        currentState = stateHashMap.get(0);
    }

    public static StateManager getInstance(){
        if(stateManager == null){
            stateManager = new StateManager();
        }

        return stateManager;
    }

    public void setCurrentState(Class<? extends State> state){
        stateHashMap.entrySet().stream().filter(stateEntry -> state.equals(stateEntry.getValue().getClass())).forEach(stateEntry -> currentState = stateEntry.getValue());
        currentState.init();
    }

    public State getCurrentState(){
        return currentState;
    }

    public HashMap<Integer, State> getStateHashMap() {
        return stateHashMap;
    }

    public State getState(int state){
        return stateHashMap.get(state);
    }

    public void add(int i, State state){
        stateHashMap.put(i, state);
    }

    public int indexOf(Class<? extends State> state){
        for(HashMap.Entry<Integer, State> stateEntry : stateHashMap.entrySet()){
            if(stateEntry.getValue().getClass().equals(state)){
                return stateEntry.getKey();
            }
        }

        return -1;
    }
}
