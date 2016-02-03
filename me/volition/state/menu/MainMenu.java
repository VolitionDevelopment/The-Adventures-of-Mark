package me.volition.state.menu;

import me.volition.state.StateManager;
import me.volition.util.ImageManager;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class MainMenu extends MenuState {

    public MainMenu(){
        super(new ImageManager().loadImage("/assets/image/menus/mainmenu.png"), new String[]{"Play", "Help", "Quit"}, new Color(0, 0, 0), new Color(255, 0, 0));
    }

    @Override
    public void select(int index) {
        if (index == 0) //Play
            StateManager.setCurrentState(StateManager.GAME_INDEX);
        else if (index == 1) //Help
            StateManager.setCurrentState(StateManager.HELP_MENU_INDEX);
        else //Quit
            System.exit(0);
    }
}
