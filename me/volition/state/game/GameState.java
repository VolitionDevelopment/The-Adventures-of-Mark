package me.volition.state.game;

import me.volition.entity.Player;
import me.volition.state.State;
import me.volition.state.StateManager;
import me.volition.util.GameManager;

import java.awt.*;

/**
 * Created by Demerzel on 2/3/16.
 */
public class GameState implements State {
    GameManager gameManager = GameManager.getInstance();

    @Override
    public void init() {

    }

    @Override
    public void update() {
        if (gameManager.getPlayer().isDead())
            StateManager.setCurrentState(StateManager.MAIN_MENU_INDEX);
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
