package volition.adv_of_mark.util;

import volition.adv_of_mark.location.impl.ApartmentRoom;
import volition.adv_of_mark.state.game.GameState;

/**
 * Created by Demerzel on 2/3/16.
 */
public class GameManager {

    private static GameManager gameManager;
    private static GameState gameState;

    public GameManager(){
        gameState = new GameState();
    }

    public static GameManager getInstance(){

        if(gameManager == null) {
            // start the game
            
            gameManager = new GameManager();

            gameState.setMap(LocationManager.loadDungeon(LocationManager.APARTMENT));
            gameState.getCurrentLocation().enterRoom();
        }

        return gameManager;

    }

    public GameState getGameState(){

        if (gameState == null)
            gameState = new GameState();

        return gameState;

    }
}
