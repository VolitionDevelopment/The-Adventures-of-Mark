package me.volition.state.game;

import me.volition.location.tile.Tile;
import me.volition.mapObject.entity.Player;
import me.volition.location.Location;
import me.volition.state.State;
import me.volition.state.StateManager;
import me.volition.state.menu.impl.MainMenu;
import me.volition.state.menu.ingamemenu.InGameMenu;
import me.volition.state.menu.ingamemenu.game.PauseMenu;
import me.volition.util.GameManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Demerzel on 2/3/16.
 */
public class GameState implements State {

    private Player player;
    private Location currentLocation;
    private InGameMenu inGameMenu;

    public GameState() {
        player = new Player(4 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE);
    }

    public Player getPlayer(){
        return player;
    }

    public Location getCurrentLocation(){
        return currentLocation;
    }

    public void setInGameMenu(InGameMenu inGameMenu){
        this.inGameMenu = inGameMenu;
    }

    public void setLocation(Location currentLocation){
        this.currentLocation = currentLocation;
    }

    @Override
    public void init() {}

    @Override
    public void update(double delta) {

        if (inGameMenu == null) {

            player.update(delta);
            currentLocation.update(delta);
            currentLocation.adjustCamera(delta);

            if (player.isDead())
                StateManager.setCurrentState(new MainMenu());

        } else
            inGameMenu.update();

    }

    @Override
    public void render(Graphics2D g) {

        GameManager.getInstance().getGameState().getCurrentLocation().render(g);

        if (inGameMenu != null)
            inGameMenu.render(g);

    }

    @Override
    public void keyPressed(int k) {

        if (inGameMenu == null) {
            if (k == KeyEvent.VK_W)
                player.setGoingUp(true);
            else if (k == KeyEvent.VK_S)
                player.setGoingDown(true);
            else if (k == KeyEvent.VK_A)
                player.setGoingLeft(true);
            else if (k == KeyEvent.VK_D)
                player.setGoingRight(true);
            else if (k == KeyEvent.VK_E)
                player.inspect();
            else if (k == KeyEvent.VK_ESCAPE)
                inGameMenu = new PauseMenu(player);
        } else
            inGameMenu.keyPressed(k);

    }

    @Override
    public void keyReleased(int k) {
        if (inGameMenu == null) {
            if (k == KeyEvent.VK_W)
                player.setGoingUp(false);
            else if (k == KeyEvent.VK_S)
                player.setGoingDown(false);
            else if (k == KeyEvent.VK_A)
                player.setGoingLeft(false);
            else if (k == KeyEvent.VK_D)
                player.setGoingRight(false);
        }
    }
}
