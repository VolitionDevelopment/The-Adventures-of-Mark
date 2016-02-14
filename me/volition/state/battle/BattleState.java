package me.volition.state.battle;

import me.volition.Window;
import me.volition.entity.Entity;
import me.volition.entity.Player;
import me.volition.location.tile.Tile;
import me.volition.state.State;
import me.volition.state.StateManager;
import me.volition.state.menu.ingamemenu.battle.BattleMainMenu;
import me.volition.state.menu.ingamemenu.battle.BattleMenu;
import me.volition.util.GameManager;
import me.volition.util.RenderUtils;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mccloskeybr on 2/6/2016.
 */
public class BattleState extends State {

    private ArrayList<Entity> enemies;
    private Player player;
    private BattleMenu battleMenu;

    private float currentConvTime, maxConvTime;
    private boolean conversation;

    private boolean playerTurn;

    private BufferedImage bgTile;

    public BattleState(BufferedImage bgTile){
        enemies = new ArrayList<>();
        playerTurn = true;
        battleMenu = new BattleMainMenu(this);
        this.bgTile = bgTile;
        maxConvTime = 50f;

        init();
    }

    public void startConversation(){
        conversation = true;
    }

    public void setEnemies(ArrayList<Entity> enemies){
        this.enemies = enemies;
        enemies.stream().filter(e -> e != null).forEach(e -> e.setAnimator(e.getBattleAnimator()));
    }

    public ArrayList<Entity> getEnemies(){
        return enemies;
    }

    public ArrayList<Entity> getLiveEnemies(){
        ArrayList<Entity> liveEnemies = new ArrayList<>(enemies);
        for (int i = 0; i < liveEnemies.size(); i++) {
            if (liveEnemies.get(i) == null) {
                liveEnemies.remove(i);
                i--;
            }
        }
        return liveEnemies;
    }

    public String[] getEnemies_strarr() {
        ArrayList<Entity> liveEnemies = getLiveEnemies();
        String[] strarr = new String[liveEnemies.size()];
        for (int i = 0; i < strarr.length; i++)
            strarr[i] = liveEnemies.get(i).getName();

        return strarr;
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

    public void setCurrentMenu(BattleMenu menu){
        battleMenu = menu;
    }

    @Override
    public void init() {}

    @Override
    public void update(double delta) {

        enemies.stream().filter(e -> e != null).forEach(e -> e.update(delta));

        player.update(delta);

        if (conversation){
            currentConvTime += delta;

            if (currentConvTime >= maxConvTime) {
                currentConvTime = 0;
                conversation = false;

                player.setSpeech(null);
                enemies.stream().filter(e -> e != null).forEach(e -> e.setSpeech(null));
            }

        } else {

            if (playerTurn)
                battleMenu.update();
            else {
                Random random = new Random();
                enemies.stream().filter(e -> e != null).forEach(e -> {
                    e.useMove(random.nextInt(e.getMoves().size()), player);
                    conversation = true;
                });
                switchTurns();

                battleMenu = new BattleMainMenu(this);
            }
        }

    }

    public void switchTurns(){
        //checks to see if any entities are dead
        if (player.getTolerance() <= 0)
            System.exit(0);
        else {
            boolean isDone = true;
            for (Entity e : enemies)
                if (e != null)
                    isDone = false;
            if (isDone) {

                player.setInBattle(false);
                StateManager.setCurrentState(GameManager.getGameState());
            }
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i) != null && enemies.get(i).getTolerance() <= 0)
                    enemies.set(i, null);
            }
        }

        playerTurn = !playerTurn;
    }

    @Override
    public void render(Graphics g) {

        g.setFont(new Font("Determination Sans", Font.PLAIN, 14));

        //render BG
        for (int i = 0; i < 7; i++)
            for (int j = 0; j < 13; j++)
                g.drawImage(bgTile, j * Tile.TILE_SIZE, i * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, null);

        //render entities
        player.render(g, 4 * Tile.TILE_SIZE, 4 * Tile.TILE_SIZE - Tile.TILE_SIZE / 2);
        if (player.getSpeech() != null) {
            RenderUtils.drawOutlinedBox(g, 4 * Tile.TILE_SIZE - g.getFontMetrics().stringWidth(player.getSpeech()) - 10, 3 * Tile.TILE_SIZE, g.getFontMetrics().stringWidth(player.getSpeech()) + 20, 30);
            g.setColor(Color.WHITE);
            g.drawString(player.getSpeech(), 4 * Tile.TILE_SIZE - g.getFontMetrics().stringWidth(player.getSpeech()), 3 * Tile.TILE_SIZE + 20);
        }


        //assuming a max number of enemies = 3
        for (int i = 0; i < enemies.size(); i++){
            if (enemies.get(i) !=  null) {
                int x = 7 * Tile.TILE_SIZE;
                int y;
                if (enemies.size() == 3)
                    y = Tile.TILE_SIZE / 2 + i * 2 * Tile.TILE_SIZE;
                else if (enemies.size() == 2)
                    y = Tile.TILE_SIZE / 2 + Tile.TILE_SIZE + i * 3 * Tile.TILE_SIZE;
                else
                    y = Tile.TILE_SIZE / 2 + 2 * Tile.TILE_SIZE;

                enemies.get(i).render(g, x, y, 128, 128);

                if (enemies.get(i).getSpeech() != null) {
                    RenderUtils.drawOutlinedBox(g, x + 128 - 10, y, g.getFontMetrics().stringWidth(enemies.get(i).getSpeech()) + 20, 30);
                    g.setColor(Color.WHITE);
                    g.drawString(enemies.get(i).getSpeech(), x + 128, y + 20);
                }

            }
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
            if (enemies.get(i) != null) {
                g.drawString("" + enemies.get(i).getName() + " : " + enemies.get(i).getTolerance() + "/" + enemies.get(i).getBaseTolerance()
                        , Window.WINDOW_WIDTH / 2 + 15, 3 * Window.WINDOW_HEIGHT / 4 + 30 * (i + 1));
                g.drawRect(Window.WINDOW_WIDTH / 2 + 150, 3 * Window.WINDOW_HEIGHT / 4 + 30 * (i + 1) - 10, 100, 10);
                g.fillRect(Window.WINDOW_WIDTH / 2 + 150, 3 * Window.WINDOW_HEIGHT / 4 + 30 * (i + 1) - 10,
                        (int) ((enemies.get(i).getTolerance() * 1.0 / enemies.get(i).getBaseTolerance()) * 100), 10);
            }
        }
    }

    @Override
    public void keyPressed(int k) {
        if (playerTurn && !conversation)
            battleMenu.keyPressed(k);
    }

    @Override
    public void keyReleased(int k) {}
}
