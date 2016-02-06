package me.volition.state.menu;

import me.volition.*;
import me.volition.Window;
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
        super(new ImageManager().loadImage("/me/volition/assets/image/menus/helpmenu.png"), new String[] {"Go Back"}, Color.WHITE, new Color(255, 0, 0));
    }

    @Override
    public void select(int index) {
        if (index == 0)
            StateManager.setCurrentState(StateManager.MAIN_MENU_INDEX);
    }

    @Override
    public void render(Graphics g){
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);

        super.render(g);
        
        g.setColor(Color.WHITE);
        String[] lines = {
                "This game was made by super close buddies Brendan 'Otaku' McCloskey and Jackson 'Scrubby' Yeager.",
                "They have been making games since they were twelve and have been improving ever since.",
                "The duo's previous games include: ",
                "    - Ball Test",
                "    - Zebra in Pinkland",
                "    - Learning to Count",
                "    - Learning to Count: Retribution",
                "    - 3D Rock Paper Scissors Lizard Spock",
                "    - Learning to Count: Reloaded ",
                "    - Scrubby the Mop ",
                "This is their last game before going to college, so they will make this one their best yet!"
        };


        g.setColor(Color.WHITE);
        RenderUtils.drawCenteredText(g, "Welcome to The Adventures of Mark!", 40, 40);

        int y = 80;
        for (String line : lines) {
            RenderUtils.drawLeftText(g, line, y, 16);
            y += 20;
        }
    }
}
