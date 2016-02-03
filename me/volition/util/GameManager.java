package me.volition.util;

import me.volition.entity.Player;
import me.volition.location.Location;

/**
 * Created by Demerzel on 2/3/16.
 */
public class GameManager {
    private static GameManager gameManager;
    private Player player;

    private GameManager(){
        player = new Player(new Location());
    }

    public static GameManager getInstance(){
        if(gameManager == null){
            gameManager = new GameManager();
            return gameManager;
        }

        return gameManager;
    }

    public Player getPlayer(){
        return player;
    }
}
