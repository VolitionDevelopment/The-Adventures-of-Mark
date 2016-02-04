package me.volition.util;

import me.volition.entity.Player;
import me.volition.location.Exit;
import me.volition.location.Location;
import me.volition.location.impl.Room;
import me.volition.location.impl.MarkApartment;

/**
 * Created by Demerzel on 2/3/16.
 */
public class GameManager {
    private static GameManager gameManager;
    private Player player;

    private GameManager(){
        Location start = new MarkApartment();
        Location room = new Room();

        start.addExit(new Exit(20, 20, room, true));
        room.addExit(new Exit(20, 20, start, true));

        player = new Player(new MarkApartment());
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
