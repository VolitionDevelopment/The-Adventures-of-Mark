package me.volition.util;

import me.volition.mapObject.entity.Player;
import me.volition.location.Location;
import me.volition.location.impl.MarkApartment;
import me.volition.state.game.GameState;

/**
 * Created by Demerzel on 2/3/16.
 */
public class GameManager {
    private static GameManager gameManager;
    private static GameState gameState;
    private static Player player;

    public GameManager(){

        Location start = new MarkApartment();
        player = new Player(start);

    }

    public static GameManager getInstance(){

        if(gameManager == null)
            newGame();

        return gameManager;

    }

    public static void newGame(){

        gameManager = new GameManager();
        player.getLocation().enterRoom();

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
