package me.volition.state.battle;

import me.volition.entity.Entity;
import me.volition.entity.Player;
import me.volition.location.tile.Tile;
import me.volition.state.State;
import me.volition.state.StateManager;
import me.volition.util.GameManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by mccloskeybr on 2/6/2016.
 */
public class BattleState extends State {

    private ArrayList<Entity> enemies;
    private Player player;

    private int currentEnemy;

    private boolean playerTurn;

    //need a battle menu, not a state rather an overlay menu that has sub menus

    public BattleState(){
        enemies = new ArrayList<>();
        playerTurn = true;
    }

    public void setEnemies(ArrayList<Entity> enemies){
        this.enemies = enemies;
        playerTurn = true;
    }

    public void setPlayer(Player player){
        this.player = player;
    }

    @Override
    public void init() {
        currentEnemy = 0;
    }

    @Override
    public void update(double delta) {
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
            enemies.get(i).render(g, x * Tile.TILE_SIZE, (2 + i) * Tile.TILE_SIZE);
        }

    }

    @Override
    public void keyPressed(int k) {
        if (playerTurn) {
            if (k == KeyEvent.VK_S)
                currentEnemy++;
            else if (k == KeyEvent.VK_W)
                currentEnemy--;

            //in bounds
            if (currentEnemy < 0)
                currentEnemy = enemies.size() - 1;
            else if (currentEnemy >= enemies.size())
                currentEnemy = 0;

        }
    }

    @Override
    public void keyReleased(int k) {}
}
