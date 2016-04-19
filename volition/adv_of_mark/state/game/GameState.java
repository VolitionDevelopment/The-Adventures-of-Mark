package volition.adv_of_mark.state.game;

import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.state.State;
import volition.adv_of_mark.state.menu.ingamemenu.InGameMenu;
import volition.adv_of_mark.state.menu.ingamemenu.game.PauseMenu;
import volition.adv_of_mark.util.GameManager;
import volition.adv_of_mark.util.LocationManager;

import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * Created by Demerzel on 2/3/16.
 */
public class GameState implements State {

    private Player player;
    private InGameMenu inGameMenu;

    public GameState() {
        player = new Player(7 * Tile.TILE_SIZE, 7 * Tile.TILE_SIZE);
    }

    public Player getPlayer(){
        return player;
    }

    public void setInGameMenu(InGameMenu inGameMenu){
        this.inGameMenu = inGameMenu;
    }

    @Override
    public void init() {}

    @Override
    public void update(double delta) {

        if (inGameMenu == null) {

            player.update(delta);
            LocationManager.getCurrentLocation().update(delta);

        } else
            inGameMenu.update();

    }

    @Override
    public void render(Graphics2D g) {

        LocationManager.getCurrentLocation().render(g);

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
