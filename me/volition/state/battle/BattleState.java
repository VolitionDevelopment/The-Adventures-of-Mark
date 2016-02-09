package me.volition.state.battle;

import me.volition.entity.Entity;
import me.volition.entity.Player;
import me.volition.location.tile.Tile;
import me.volition.state.State;
import me.volition.state.StateManager;
import me.volition.state.menu.ingamemenu.BattleMenu;
import me.volition.state.menu.ingamemenu.InGameMenu;
import me.volition.util.GameManager;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by mccloskeybr on 2/6/2016.
 */
public class BattleState extends State {

    private ArrayList<Entity> enemies;
    private Player player;
    private InGameMenu battleMenu;

    private boolean playerTurn;

    public BattleState(){
        enemies = new ArrayList<>();
        playerTurn = true;
        battleMenu = new BattleMenu(this);
        init();
    }

    public void setEnemies(ArrayList<Entity> enemies){
        this.enemies = enemies;
        playerTurn = true;
    }

    public ArrayList<Entity> getEnemies(){
        return enemies;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    public Player getPlayer(){
        return player;
    }

    public void setCurrentMenu(InGameMenu menu){
        battleMenu = menu;
    }

    @Override
    public void init() {}

    @Override
    public void update(double delta) {
        battleMenu.update();
        if (player.getTolerance() <= 0)
            System.exit(0);
        else {
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).getTolerance() <= 0) {
                    enemies.remove(i);
                    if (enemies.size() == 0)
                        StateManager.setCurrentState(GameManager.getGameState()); //resume game after battle concludes
                    i--;
                }
            }
        }
    }

    @Override
    public void render(Graphics g) {
        player.render(g, 3 * Tile.TILE_SIZE, 4 * Tile.TILE_SIZE);

        int x = 4;
        for (int i = 0; i < enemies.size(); i++){
            if (i % 3 == 0)
                x++;
            //enemies.get(i).render(g, x * Tile.TILE_SIZE, (2 + i) * Tile.TILE_SIZE);
        }

        battleMenu.render(g);
    }

    @Override
    public void keyPressed(int k) {
        if (playerTurn)
            battleMenu.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {}
}
