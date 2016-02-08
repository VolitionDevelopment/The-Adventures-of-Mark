package me.volition.util;

import me.volition.Window;
import me.volition.entity.Player;
import me.volition.location.Exit;
import me.volition.location.Location;
import me.volition.location.impl.MarkApartment;
import me.volition.location.impl.Room;
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
        Location room = new Room();

        start.addExit(new Exit(Window.WINDOW_WIDTH - 100, Window.WINDOW_HEIGHT / 2 - 50, 100, 150, room, true));
        room.addExit(new Exit(Window.WINDOW_WIDTH / 2, 0, 100, 100, start, true));

        player = new Player(start);
    }

    public static GameManager getInstance(){
        if(gameManager == null)
            gameManager = new GameManager();

        return gameManager;
    }

    public static GameState newGame(){
        gameState = new GameState();
        return gameState;
    }

    public static GameState getGameState(){
        return gameState;
    }

    public Player getPlayer(){
        return player;
    }
}
