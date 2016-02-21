package me.volition.state.menu.impl;

import me.volition.Window;
import me.volition.state.menu.MenuState;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/20/2016.
 */
public class LoadMenu extends MenuState {
    public LoadMenu() {
        super(null, new String[]{""}, Color.BLACK, Color.BLACK);
    }

    @Override
    public void select(int currentIndex) {}

    @Override
    public void render(Graphics2D g) {
        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);
    }
}
