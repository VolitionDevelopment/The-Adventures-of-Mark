package volition.adv_of_mark.state.menu.impl;

import volition.adv_of_mark.Window;
import volition.adv_of_mark.state.StateManager;
import volition.adv_of_mark.state.menu.BottomTextMenu;
import volition.adv_of_mark.util.ImageManager;
import volition.adv_of_mark.util.RenderUtils;

import java.awt.*;

/**
 * Created by Demerzel on 2/4/16.
 */
public class AboutMenu extends BottomTextMenu {
    public AboutMenu() {
        super(ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/menus/helpmenu.png"), new String[] {"Go Back"}, Color.WHITE, new Color(255, 0, 0));
    }

    @Override
    public void select(int index) {
        if (index == 0)
            StateManager.setCurrentState(new MainMenu());
    }

    @Override
    public void render(Graphics2D g){
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
                "This is their last game before going to college, so they will make this one their best yet!",
                "",
                "Music Credits:",
                "Walkaround - 8-Bit Classical, 8 Bit remix / Sheep May Safely Graze (Bach)",
                "Battle - RPD310, 8 bit remix / Simple and Clean (Kingdom Hearts)",
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
