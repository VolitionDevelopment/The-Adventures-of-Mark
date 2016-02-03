package me.volition.state.game;

import me.volition.entity.Player;
import me.volition.location.Location;
import me.volition.state.State;
import me.volition.state.StateManager;
import me.volition.state.menu.MainMenu;
import me.volition.util.GameManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Demerzel on 2/3/16.
 */
public class GameState extends State {
    GameManager gameManager = GameManager.getInstance();

    private Player player;

    public GameState() {
        super(0);
        player = gameManager.getPlayer();
    }

    @Override
    public void init() {

    }

    @Override
    public void update(double delta) {
        StateManager manager = StateManager.getInstance();

        Location currentLocation = gameManager.getPlayer().getLocation();
        gameManager.getPlayer().update(delta);

        if (gameManager.getPlayer().isDead())
            manager.setCurrentState(MainMenu.class);
    }

    @Override
    public void render(Graphics g) {
        gameManager.getPlayer().getLocation().render(g);
        player.render(g);
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_W)
            gameManager.getPlayer().setGoingUp(true);
        else if (k == KeyEvent.VK_S)
            gameManager.getPlayer().setGoingDown(true);
        else if (k == KeyEvent.VK_A)
            gameManager.getPlayer().setGoingLeft(true);
        else if (k == KeyEvent.VK_D)
            gameManager.getPlayer().setGoingRight(true);
    }

    @Override
    public void keyReleased(int k) {
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
