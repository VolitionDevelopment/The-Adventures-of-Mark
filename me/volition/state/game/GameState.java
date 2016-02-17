package me.volition.state.game;

import me.volition.entity.Player;
import me.volition.location.Location;
import me.volition.state.State;
import me.volition.state.StateManager;
import me.volition.state.menu.impl.MainMenu;
import me.volition.state.menu.ingamemenu.InGameMenu;
import me.volition.state.menu.ingamemenu.game.PauseMenu;
import me.volition.util.GameManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Demerzel on 2/3/16.
 */
public class GameState implements State {

    private Player player;
    private InGameMenu inGameMenu;

    public GameState() {
        player = GameManager.getInstance().getPlayer();
    }

    public void setInGameMenu(InGameMenu inGameMenu){
        player.stopMoving();

        this.inGameMenu = inGameMenu;
    }

    @Override
    public void init() {}

    @Override
    public void update(double delta) {

        if (inGameMenu == null) {

            Location currentLocation = GameManager.getInstance().getPlayer().getLocation();

            player.update(delta);
            currentLocation.update(delta);
            currentLocation.adjustCamera(delta);

            if (GameManager.getInstance().getPlayer().isDead())
                StateManager.setCurrentState(new MainMenu());

        } else
            inGameMenu.update();

    }

    @Override
    public void render(Graphics g) {

        player.getLocation().render(g);
        player.render(g);

        if (inGameMenu != null)
            inGameMenu.render(g);

    }

    @Override
    public void keyPressed(int k) {

        if (inGameMenu == null) {
            if (k == KeyEvent.VK_W)
                GameManager.getInstance().getPlayer().setGoingUp(true);
            else if (k == KeyEvent.VK_S)
                GameManager.getInstance().getPlayer().setGoingDown(true);
            else if (k == KeyEvent.VK_A)
                GameManager.getInstance().getPlayer().setGoingLeft(true);
            else if (k == KeyEvent.VK_D)
                GameManager.getInstance().getPlayer().setGoingRight(true);
            else if (k == KeyEvent.VK_E)
                player.inspect();
            else if (k == KeyEvent.VK_ESCAPE)
                inGameMenu = new PauseMenu(player);
        } else
            inGameMenu.keyPressed(k);

    }

    @Override
    public void keyReleased(int k) {
        if (inGameMenu == null) {
            if (k == KeyEvent.VK_W)
                GameManager.getInstance().getPlayer().setGoingUp(false);
            else if (k == KeyEvent.VK_S)
                GameManager.getInstance().getPlayer().setGoingDown(false);
            else if (k == KeyEvent.VK_A)
                GameManager.getInstance().getPlayer().setGoingLeft(false);
            else if (k == KeyEvent.VK_D)
                GameManager.getInstance().getPlayer().setGoingRight(false);
        }
    }
}
