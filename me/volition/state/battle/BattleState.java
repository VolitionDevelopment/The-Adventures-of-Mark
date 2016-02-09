package me.volition.state.battle;

import me.volition.Window;
import me.volition.entity.Entity;
import me.volition.entity.Player;
import me.volition.location.tile.Tile;
import me.volition.location.tile.WoodTile;
import me.volition.state.State;
import me.volition.state.StateManager;
import me.volition.state.menu.ingamemenu.BattleMenu;
import me.volition.state.menu.ingamemenu.InGameMenu;
import me.volition.util.GameManager;
import me.volition.util.RenderUtils;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mccloskeybr on 2/6/2016.
 */
public class BattleState extends State {

    private ArrayList<Entity> enemies;
    private Player player;
    private InGameMenu battleMenu;

    private boolean playerTurn;

    private Tile[][] tilemap;

    public BattleState(){
        enemies = new ArrayList<>();
        playerTurn = true;
        battleMenu = new BattleMenu(this);

        tilemap = new Tile[7][13];
        for (int i = 0; i < tilemap.length; i++)
            for (int j = 0; j < tilemap[i].length; j++)
                tilemap[i][j] = new WoodTile(j * Tile.TILE_SIZE, i * Tile.TILE_SIZE);

        init();
    }

    public void setPlayerTurn(boolean playerTurn){
        this.playerTurn = playerTurn;
    }

    public void setEnemies(ArrayList<Entity> enemies){
        this.enemies = enemies;
        for (Entity e: enemies)
            e.setAnimator(e.getBattleAnimator());
    }

    public ArrayList<Entity> getEnemies(){
        return enemies;
    }

    public String[] getEnemies_strarr(){
        String[] arr = new String[enemies.size()];
        for (int i = 0; i < arr.length; i++)
            arr[i] = enemies.get(i).getName();
        return arr;
    }

    public Entity getEnemy(int index){
        return enemies.get(index);
    }

    public void setPlayer(Player player){
        this.player = player;
        player.setInBattle(true);
        player.setAnimator(player.getBattleAnimator());
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
        for (Entity e: enemies)
            e.update(delta);

        player.update(delta);

        if (playerTurn)
            battleMenu.update();
        else {
            Random random = new Random();
            for (Entity e : enemies)
                e.useMove(random.nextInt(e.getMoves().size()), player);

            playerTurn = true;
            battleMenu = new BattleMenu(this);
        }

        if (player.getTolerance() <= 0)
            System.exit(0);
        else if (enemies.size() == 0) {
            player.setInBattle(false);
            player.setX(player.getX() + Tile.TILE_SIZE);
            StateManager.setCurrentState(GameManager.getGameState());
        } else {
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i).getTolerance() <= 0) {
                    enemies.remove(i);
                    i--;
                }
            }
        }

    }

    @Override
    public void render(Graphics g) {

        //render BG
        for (int i = 0; i < tilemap.length; i++)
            for (int j = 0; j < tilemap[i].length; j++)
                tilemap[i][j].render(g);

        //render entities
        player.render(g, 4 * Tile.TILE_SIZE, 3 * Tile.TILE_SIZE);

        int x = 5;
        for (int i = 0; i < enemies.size(); i++){
            if (i % 3 == 0)
                x += 2;
            enemies.get(i).render(g, x * Tile.TILE_SIZE, (i * 2 * Tile.TILE_SIZE), 128, 128);
        }

        //render menus
        RenderUtils.drawOutlinedBox(g, 0, 3 * Window.WINDOW_HEIGHT / 4 - 30, Window.WINDOW_WIDTH / 2, Window.WINDOW_HEIGHT / 4 + 20);
        RenderUtils.drawOutlinedBox(g, Window.WINDOW_WIDTH / 2 - 5, 3 * Window.WINDOW_HEIGHT / 4 - 30, Window.WINDOW_WIDTH / 2, Window.WINDOW_HEIGHT / 4 + 20);
        battleMenu.render(g);

        g.setColor(Color.WHITE);
        g.drawString("" + player.getName() + " : " + player.getTolerance() + "/" + player.getBaseTolerance()
            , Window.WINDOW_WIDTH / 2 + 15, 3 * Window.WINDOW_HEIGHT / 4);
        g.drawRect(Window.WINDOW_WIDTH / 2 + 150, 3 * Window.WINDOW_HEIGHT / 4 - 10, 100, 10);
        g.fillRect(Window.WINDOW_WIDTH / 2 + 150, 3 * Window.WINDOW_HEIGHT / 4 - 10, (int) ((player.getTolerance() * 1.0 / player.getBaseTolerance()) * 100), 10);
        for (int i = 0; i < enemies.size(); i++) {
            g.drawString("" + enemies.get(i).getName() + " : " + enemies.get(i).getTolerance() + "/" + enemies.get(i).getBaseTolerance()
                    , Window.WINDOW_WIDTH / 2 + 15, 3 * Window.WINDOW_HEIGHT / 4 + 30 * (i + 1));
            g.drawRect(Window.WINDOW_WIDTH / 2 + 150, 3 * Window.WINDOW_HEIGHT / 4 + 30 * (i + 1) - 10, 100, 10);
            g.fillRect(Window.WINDOW_WIDTH / 2 + 150, 3 * Window.WINDOW_HEIGHT / 4 + 30 * (i + 1) - 10,
                    (int) ((enemies.get(i).getTolerance() * 1.0 / enemies.get(i).getBaseTolerance()) * 100), 10);
        }
    }

    @Override
    public void keyPressed(int k) {
        if (playerTurn)
            battleMenu.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {}
}
