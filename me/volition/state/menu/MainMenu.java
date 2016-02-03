package me.volition.state.menu;

import me.volition.state.State;
import me.volition.state.StateManager;
import me.volition.state.game.GameState;
import me.volition.util.ImageManager;

import java.awt.*;
import java.util.HashMap;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class MainMenu extends MenuState {

    public MainMenu(){
        super(new ImageManager().loadImage("/assets/image/menus/mainmenu.png"), new String[]{"Play", "Help", "Quit"}, new Color(0, 0, 0), new Color(255, 0, 0), 0);
    }

    @Override
    public void select(int index) {
        StateManager manager = StateManager.getInstance();

        try{
            manager.setCurrentState(manager.getState(index).getClass());
        }catch(NullPointerException e){
            System.exit(0);
        }
    }
}
