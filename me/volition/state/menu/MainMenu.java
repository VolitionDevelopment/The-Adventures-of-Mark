package me.volition.state.menu;

import me.volition.state.StateManager;
import me.volition.util.ImageManager;
import me.volition.util.RenderUtils;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class MainMenu extends CenterTextMenu {

    private double tick;
    private int fontSize = 70;
    private boolean increasing = false;

    public MainMenu(){
        super(new ImageManager().loadImage("/me/volition/assets/image/menus/mainmenu.png"), new String[]{"Play", "Help", "About", "Quit"}, new Color(255, 255, 255), new Color(255, 0, 0));
    }

    @Override
    public void update(double delta){
        float threshold = 2;

        tick += delta * 1;
        if (tick >= threshold){
            if (increasing) {
                fontSize++;
                if (fontSize >= 72)
                    increasing = false;
            } else {
                fontSize--;
                if (fontSize <= 68)
                    increasing = true;

            }
            tick = 0;
        }
    }

    @Override
    public void render(Graphics g){
        super.render(g);

        RenderUtils.drawCenteredText(g, "Adventures of Mark", 80, fontSize);

    }

    @Override
    public void select(int index) {
        //start game
        if (index == 0)
            StateManager.setCurrentState(StateManager.GAME_INDEX);

        //help
        else if (index == 1)
            StateManager.setCurrentState(StateManager.HELP_MENU_INDEX);

        //about
        else if (index == 2)
            StateManager.setCurrentState(StateManager.ABOUT_INDEX);

        //exit
        else
            System.exit(0);
    }
}
