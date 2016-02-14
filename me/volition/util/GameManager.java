package me.volition.util;

import me.volition.entity.Player;
import me.volition.location.Location;
import me.volition.location.impl.MarkApartment;
import me.volition.state.game.GameState;

import java.awt.*;

/**
 * Created by Demerzel on 2/3/16.
 */
public class GameManager {
    private static GameManager gameManager;
    private static GameState gameState;
    private Player player;

    private GameManager(){
        Location start = new MarkApartment();

        player = new Player(start);
        start.enterRoom(player);
    }

    public static GameManager getInstance(){
        if(gameManager == null)
            gameManager = new GameManager();

        return gameManager;
    }

    public GameState getGameState(){
        if (gameState == null)
            gameState = new GameState();

        return gameState;
    }

    public Player getPlayer(){
        return player;
    }
}
