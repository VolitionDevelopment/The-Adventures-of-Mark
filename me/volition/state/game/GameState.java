package me.volition.state.game;

import me.volition.entity.Player;
import me.volition.state.State;
import me.volition.state.StateManager;
import me.volition.state.menu.MainMenu;
import me.volition.util.GameManager;

import java.awt.*;

/**
 * Created by Demerzel on 2/3/16.
 */
public class GameState extends State {
    GameManager gameManager = GameManager.getInstance();

    public GameState() {
        super(0);
    }

    @Override
    public void init() {

    }

    @Override
    public void update() {
        StateManager manager = StateManager.getInstance();

        if (gameManager.getPlayer().isDead())
            manager.setCurrentState(MainMenu.class);
    }

    @Override
    public void render(Graphics g) {

    }

    @Override
    public void keyPressed(int k) {

    }

    @Override
    public void keyReleased(int k) {

    }

}
