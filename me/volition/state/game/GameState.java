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
    GameManager gameManager = GameManager.getInstance();

    private Player player;
    private InGameMenu pauseMenu;

    public GameState() {
        player = gameManager.getPlayer();
    }

    public void setPauseMenu(InGameMenu pauseMenu){
        this.pauseMenu = pauseMenu;
    }

    @Override
    public void init() {}

    @Override
    public void update(double delta) {
        if (pauseMenu == null) {
            Location currentLocation = gameManager.getPlayer().getLocation();
            player.update(delta);
            currentLocation.update(player, delta);

            if (gameManager.getPlayer().isDead())
                StateManager.setCurrentState(new MainMenu());
        } else
            pauseMenu.update();
    }

    @Override
    public void render(Graphics g) {
        player.getLocation().render(g);
        player.render(g);

        if (pauseMenu != null)
            pauseMenu.render(g);
    }

    @Override
    public void keyPressed(int k) {
        if (pauseMenu == null) {
            if (k == KeyEvent.VK_W)
                gameManager.getPlayer().setGoingUp(true);
            else if (k == KeyEvent.VK_S)
                gameManager.getPlayer().setGoingDown(true);
            else if (k == KeyEvent.VK_A)
                gameManager.getPlayer().setGoingLeft(true);
            else if (k == KeyEvent.VK_D)
                gameManager.getPlayer().setGoingRight(true);
            else if (k == KeyEvent.VK_E)
                player.inspect();
            else if (k == KeyEvent.VK_ESCAPE)
                pauseMenu = new PauseMenu(player);
        } else
            pauseMenu.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {
        if (pauseMenu == null) {
            if (k == KeyEvent.VK_W)
                gameManager.getPlayer().setGoingUp(false);
            else if (k == KeyEvent.VK_S)
                gameManager.getPlayer().setGoingDown(false);
            else if (k == KeyEvent.VK_A)
                gameManager.getPlayer().setGoingLeft(false);
            else if (k == KeyEvent.VK_D)
                gameManager.getPlayer().setGoingRight(false);
        }
    }
}
