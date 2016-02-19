package me.volition.state.menu.ingamemenu.game;

import me.volition.Window;
import me.volition.mapObject.entity.Player;
import me.volition.state.StateManager;
import me.volition.state.menu.impl.InventoryMenu;
import me.volition.state.menu.impl.StatusMenu;
import me.volition.state.menu.ingamemenu.InGameMenu;
import me.volition.util.FontManager;
import me.volition.util.GameManager;
import me.volition.util.RenderUtils;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by mccloskeybr on 2/9/2016.
 */
public class PauseMenu extends InGameMenu {

    private Player player;

    public PauseMenu(Player player) {
        super(new String[]{
                "CONT.",
                "INV.",
                "STAT.",
                "QUIT."
        });
        this.player = player;
    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            GameManager.getInstance().getGameState().setInGameMenu(null);
        else if (currentIndex == 1)
            StateManager.setCurrentState(new InventoryMenu(player));
        else if (currentIndex == 2) {
            StateManager.setCurrentState(new StatusMenu(player));
        } else
            System.exit(0);
    }

    @Override
    public void keyPressed(int k){
        super.keyPressed(k);

        if (k == KeyEvent.VK_D)
            setCurrentIndex(getCurrentIndex() + 1);
        else if (k == KeyEvent.VK_A)
            setCurrentIndex(getCurrentIndex() - 1);
    }

    @Override
    public void render(Graphics2D g) {
        RenderUtils.drawOutlinedBox(g, 0,  4 * Window.WINDOW_HEIGHT / 5, Window.WINDOW_WIDTH - 5, Window.WINDOW_HEIGHT / 5 - 5);
        g.setFont(FontManager.getSans(30));

        for (int i = 0; i < getOptions().length; i++) {
            if (i == getCurrentIndex())
                g.setColor(Color.RED);
            else
                g.setColor(Color.WHITE);

            g.drawString(getOptions()[i], 100 + 150 * i, me.volition.Window.WINDOW_HEIGHT - 75);
        }
    }
}
