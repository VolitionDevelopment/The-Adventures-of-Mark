package volition.adv_of_mark.state.battle;

import volition.adv_of_mark.Window;
import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.item.Item;
import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.state.State;
import volition.adv_of_mark.state.StateManager;
import volition.adv_of_mark.state.menu.ingamemenu.battle.BattleMainMenu;
import volition.adv_of_mark.state.menu.ingamemenu.battle.BattleMenu;
import volition.adv_of_mark.state.menu.impl.DeathMenu;
import volition.adv_of_mark.util.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mccloskeybr on 2/6/2016.
 */
public class BattleState implements State {

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

    public void setPlayer(Player player){
        this.player = player;
        player.setAbleMove(false);
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

        if (AudioManager.getInstance().musicDone())
            AudioManager.getInstance().playMusic("/volition/adv_of_mark/assets/sound/music/battle.wav");

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

                int cHealth = GameManager.getInstance().getGameState().getPlayer().getTolerance();

                Random random = new Random();
                enemies.stream().filter(e -> e != null).forEach(e -> {
                    e.useMove(random.nextInt(e.getMoves().size()), player);
                    conversation = true;
                });

                if (GameManager.getInstance().getGameState().getPlayer().getTolerance() != cHealth)
                    AudioManager.getInstance().playSound("/volition/adv_of_mark/assets/sound/effects/hit.wav");

                switchTurns();

                battleMenu = new BattleMainMenu(this);
            }
        }

    }

    public void switchTurns(){
        //checks to see if any entities are dead
        if (player.getTolerance() <= 0)
            StateManager.setCurrentState(new DeathMenu());
        else {
            boolean isDone = true;
            for (Entity e : enemies)
                if (e != null)
                    isDone = false;
            if (isDone)
                finishFight(false);
            for (int i = 0; i < enemies.size(); i++) {
                if (enemies.get(i) != null && enemies.get(i).getTolerance() <= 0)
                    enemies.set(i, null);
            }
        }

        playerTurn = !playerTurn;
    }

    public void finishFight(boolean ranaway){

        if (!ranaway) {
            Random random = new Random();

            for (int i = 0; i < enemies.size(); i++) {

                Item item = ObjectManager.getRandomUsable(10);
                if (item != null)
                    player.addItem(item);

                player.modExp(random.nextInt(50) + 20);
                player.modMoney(random.nextInt(30) + 20);
            }
        }

        player.setAbleMove(true);
        StateManager.setCurrentState(GameManager.getInstance().getGameState());

        AudioManager.getInstance().stopMusic();
    }

    @Override
    public void render(Graphics2D g) {

        g.setFont(FontManager.getSans(14));

        //render entities
        g.drawImage(bgTile, 4 * Tile.TILE_SIZE, 4 * Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE, null);
        player.render(g, 4 * Tile.TILE_SIZE, 4 * Tile.TILE_SIZE - Tile.TILE_SIZE / 2);
        if (player.getSpeech() != null) {
            int x = 4 * Tile.TILE_SIZE - g.getFontMetrics().stringWidth(player.getSpeech());
            if (x < 0)
                x = 10;

            g.setColor(Color.WHITE);
            RenderUtils.drawBoxedWrappedText(g, player.getSpeech(), x, 3 * Tile.TILE_SIZE + 20, 4 * Tile.TILE_SIZE - x + 20);
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

                g.drawImage(bgTile, x, y + Tile.TILE_SIZE, Tile.TILE_SIZE * 2, Tile.TILE_SIZE * 2, null);
                enemies.get(i).render(g, x, y, 128, 128);

                if (enemies.get(i).getSpeech() != null) {
                    int width = Window.WINDOW_WIDTH - (x + 128) - 20;
                    if (g.getFontMetrics().stringWidth(enemies.get(i).getSpeech()) < width)
                        width = g.getFontMetrics().stringWidth(enemies.get(i).getSpeech());

                    g.setColor(Color.WHITE);
                    RenderUtils.drawBoxedWrappedText(g, enemies.get(i).getSpeech(), x + 128, y + 20, width + 20);
                }
            }
        }

        //render menus
        RenderUtils.drawOutlinedBox(g, 0, 3 * Window.WINDOW_HEIGHT / 4 - 30, Window.WINDOW_WIDTH / 2, Window.WINDOW_HEIGHT / 4 + 20);
        RenderUtils.drawOutlinedBox(g, Window.WINDOW_WIDTH / 2 - 5, 3 * Window.WINDOW_HEIGHT / 4 - 30, Window.WINDOW_WIDTH / 2, Window.WINDOW_HEIGHT / 4 + 20);
        battleMenu.render(g);

        g.setColor(Color.WHITE);
        g.drawString(player.getName() + " : " + player.getTolerance() + "/" + player.getBaseTolerance()
            , Window.WINDOW_WIDTH / 2 + 15, 3 * Window.WINDOW_HEIGHT / 4);
        g.setColor(Color.RED);
        g.fillRect(Window.WINDOW_WIDTH / 2 + 150, 3 * Window.WINDOW_HEIGHT / 4 - 10, (int) ((player.getTolerance() * 1.0 / player.getBaseTolerance()) * 100), 5);
        g.setColor(Color.BLUE);
        g.fillRect(Window.WINDOW_WIDTH / 2 + 150, 3 * Window.WINDOW_HEIGHT / 4 - 10 + 5, (int) ((player.getBrainpower() * 1.0 / player.getBaseBrainpower()) * 100), 5);
        g.setColor(Color.WHITE);
        g.drawRect(Window.WINDOW_WIDTH / 2 + 150, 3 * Window.WINDOW_HEIGHT / 4 - 10, 100, 5);
        g.drawRect(Window.WINDOW_WIDTH / 2 + 150, 3 * Window.WINDOW_HEIGHT / 4 - 10 + 5, 100, 5);
        for (int i = 0; i < enemies.size(); i++) {
            if (enemies.get(i) != null) {
                g.drawString("" + enemies.get(i).getName() + " : " + enemies.get(i).getTolerance() + "/" + enemies.get(i).getBaseTolerance()
                        , Window.WINDOW_WIDTH / 2 + 15, 3 * Window.WINDOW_HEIGHT / 4 + 30 * (i + 1));
                g.setColor(Color.RED);
                g.fillRect(Window.WINDOW_WIDTH / 2 + 150, 3 * Window.WINDOW_HEIGHT / 4 + 30 * (i + 1) - 10,
                        (int) ((enemies.get(i).getTolerance() * 1.0 / enemies.get(i).getBaseTolerance()) * 100), 5);
                g.setColor(Color.BLUE);
                g.fillRect(Window.WINDOW_WIDTH / 2 + 150, 3 * Window.WINDOW_HEIGHT / 4 + 30 * (i + 1) - 10 + 5,
                        (int) ((enemies.get(i).getBrainpower() * 1.0 / enemies.get(i).getBaseBrainpower()) * 100), 5);
                g.setColor(Color.WHITE);
                g.drawRect(Window.WINDOW_WIDTH / 2 + 150, 3 * Window.WINDOW_HEIGHT / 4 + 30 * (i + 1) - 10, 100, 5);
                g.drawRect(Window.WINDOW_WIDTH / 2 + 150, 3 * Window.WINDOW_HEIGHT / 4 + 30 * (i + 1) - 10 + 5, 100, 5);
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
