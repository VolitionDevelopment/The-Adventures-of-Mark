package me.volition.util;

import me.volition.location.tile.Tile;
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
        player = new Player(4 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE);
    }

    public static GameManager getInstance(){

        if(gameManager == null) {

            gameManager = new GameManager();

            gameManager.getGameState().setLocation(new MarkApartment());
            gameManager.getGameState().getCurrentLocation().enterRoom();

        }

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
