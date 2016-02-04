package me.volition.state.menu;

import me.volition.state.StateManager;
import me.volition.util.ImageManager;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class MainMenu extends CenterTextMenu {

    public MainMenu(){
        super(new ImageManager().loadImage("/me/volition/assets/image/menus/mainmenu.png"), new String[]{"Play", "Help", "Quit"}, new Color(0, 0, 0), new Color(255, 0, 0));
    }

    @Override
    public void select(int index) {
        //start game
        if (index == 0)
            StateManager.setCurrentState(StateManager.GAME_INDEX);

        //help menu
        else if (index == 1)
            StateManager.setCurrentState(StateManager.HELP_MENU_INDEX);

        //exit
        else
            System.exit(0);
    }
}
