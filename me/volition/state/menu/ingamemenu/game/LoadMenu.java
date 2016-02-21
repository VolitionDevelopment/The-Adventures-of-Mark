package me.volition.state.menu.ingamemenu.game;

import me.volition.Window;
import me.volition.state.menu.ingamemenu.InGameMenu;
import me.volition.util.FontManager;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/20/2016.
 */
public class LoadMenu extends InGameMenu {
    public LoadMenu() {
        super(new String[]{""});
    }

    @Override
    public void select(int currentIndex) {}

    @Override
    public void render(Graphics2D g) {

        g.setFont(FontManager.getSans(24));

        g.setColor(Color.BLACK);
        g.fillRect(0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT);

        g.setColor(Color.RED);
        g.drawString("LOADING...", Window.WINDOW_WIDTH - 200, Window.WINDOW_HEIGHT - 50);
    }
}
