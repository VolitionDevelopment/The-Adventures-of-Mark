package me.volition.state.menu.impl;

import me.volition.Window;
import me.volition.component.Textbox;
import me.volition.state.StateManager;
import me.volition.state.menu.BottomTextMenu;
import me.volition.util.GameManager;
import me.volition.util.ImageManager;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public class HelpMenu extends BottomTextMenu {

    public HelpMenu(){
        super (null, new String[]{"Go back"}, Color.WHITE, Color.RED);
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

        Textbox textbox = new Textbox("Hello World", GameManager.getInstance().getGameState().getPlayer());
        textbox.render(g);
    }
}
