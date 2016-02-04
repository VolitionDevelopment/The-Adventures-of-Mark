package me.volition.state.menu;

import me.volition.state.StateManager;
import me.volition.util.ImageManager;
import me.volition.util.RenderUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/4/16.
 */
public class AboutMenu extends BottomTextMenu {
    public AboutMenu() {
        super(new ImageManager().loadImage("/me/volition/assets/image/menus/helpmenu.png"), new String[] {"Go Back"}, new Color(0, 0, 0), new Color(255, 0, 0));
    }

    @Override
    public void select(int index) {
        if (index == 0)
            StateManager.setCurrentState(StateManager.MAIN_MENU_INDEX);
    }

    @Override
    public void render(Graphics g){
        super.render(g);

        g.setColor(new Color(0, 0, 0));
        RenderUtils.drawCenteredText(g, "Welcome to The Adventures of Mark!", 20, getMenuFont());
    }
}
