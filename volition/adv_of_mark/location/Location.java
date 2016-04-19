package volition.adv_of_mark.location;

import volition.adv_of_mark.Main;
import volition.adv_of_mark.Window;
import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.MapObject;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.mapObject.entity.enemies.EnemyParty;
import volition.adv_of_mark.mapObject.placeableObject.PlaceableObject;
import volition.adv_of_mark.state.game.GameState;
import volition.adv_of_mark.state.menu.ingamemenu.game.LoadMenu;
import volition.adv_of_mark.util.*;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Demerzel on 2/3/16.
 */

public abstract class Location {

    private String name;
    private boolean hasEntered;
    private int x, y;
    private ArrayList<PlaceableObject> placeableObjects;
    private ArrayList<MapObject> perspectiveList;
    private Tile[][] tilemap;
    private BufferedImage tileImage, bgImage;
    private double bg_x, bg_y, bg_horizOffset, bg_vertOffset;
    private ArrayList<Entity> npcs;
    private ArrayList<EnemyParty> enemyParties;

    private static final int DIST_CONST = 15; //for collision detection


    public Location(String name, int x, int y) {
        placeableObjects = new ArrayList<>();
        npcs = new ArrayList<>();
        enemyParties = new ArrayList<>();

        this.x = x;
        this.y = y;

        Random rand = new Random();
        if (rand.nextInt(3) > 0) {

            int numParties = rand.nextInt(5);
            for (int i = 0; i < numParties; i++) {

                EnemyParty party = new EnemyParty((rand.nextInt(13) + 1) * Tile.TILE_SIZE, (rand.nextInt(13) + 1) * Tile.TILE_SIZE);

                int numEnemies = rand.nextInt(3) + 1;
                for (int j = 0; j < numEnemies; j++)
                    party.addEnemy();

                enemyParties.add(party);

            }
        }

        perspectiveList = new ArrayList<>();

        this.name = name;

        loadMap();

        perspectiveList.addAll(placeableObjects);
        perspectiveList.addAll(npcs);

    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public boolean hasEntered(){
        return hasEntered;
    }

    public BufferedImage getTileImage(){
        if (tileImage == null)
            tileImage = ImageManager.makeImageFromMap(getTilemap());

        return tileImage;
    }

    public ArrayList<EnemyParty> getEnemyParties(){
        return enemyParties;
    }

    public double getBg_horizOffset() {
        return bg_horizOffset + tileImage.getWidth();
    }

    public double getBg_vertOffset() {
        return bg_vertOffset + tileImage.getHeight();
    }

    public Tile[][] getTilemap(){
        return tilemap;
    }

    public void addExit(Exit exit){
        int x = (int) (exit.getX() / Tile.TILE_SIZE);
        int y = (int) (exit.getY() / Tile.TILE_SIZE);

        tilemap[y][x].setExit(exit);
    }

    public void addPlaceableObject(PlaceableObject placeableObject){

        placeableObjects.add(placeableObject);

        double x = placeableObject.getX() / Tile.TILE_SIZE;
        double y = placeableObject.getY() / Tile.TILE_SIZE;

        BufferedImage image = placeableObject.getImage();

        if (image != null) {
            int width = placeableObject.getWidth() / Tile.TILE_SIZE;
            int length = placeableObject.getLength() / Tile.TILE_SIZE;

            for (int i = 0; i < width; i++) {
                for (int j = 0; j < length; j++){
                    tilemap[(int) y + j][(int) x + i].setSolid(placeableObject.isSolid());
                    tilemap[(int) y + j][(int) x + i].setObject(placeableObject);
                }
            }
        }
    }

    public void addNpc(Entity npc){

        npcs.add(npc);

        double x = npc.getX();
        double y = npc.getY();

        tilemap[(int) y][(int) x].setSolid(true);
        tilemap[(int) y][(int) x].setNpc(npc);
    }

    public void setTilemap(Tile[][] tilemap){
        this.tilemap = tilemap;
    }

    public void update(double delta){

        Player player = GameManager.getInstance().getGameState().getPlayer();

        Tile playerTile = tilemap[((int) player.getY() + player.getHeight() / 2) / Tile.TILE_SIZE][((int) player.getX() + player.getWidth() / 2) / Tile.TILE_SIZE];

        //check if at exit
        Exit exit = playerTile.getExit();
        if (exit != null && (enemyParties.size() == 0 || exit.getLeadsTo().getEnemyParties().size() == 0)) {

            player.stopMoving();

            GameManager.getInstance().getGameState().setInGameMenu(new LoadMenu());
            Main.getInstance().repaint();

            exit.enter(player);
        }

        //check if colliding with group of enemies
        for (int i = 0; i < enemyParties.size(); i++) {
            enemyParties.get(i).update(delta);
            if (enemyParties.get(i).getX() > player.getX() && enemyParties.get(i).getX() < player.getX() + player.getWidth() &&
                    enemyParties.get(i).getY() > player.getY() && enemyParties.get(i).getY() < player.getY() + player.getHeight()) {

                player.stopMoving();
                enemyParties.get(i).startBattle();
                BattleManager.startBattle(player, enemyParties.get(i).getMembers(), playerTile.getImage());

                enemyParties.remove(i);
                i--;

            }
        }

        //objects closer to camera need to be displayed on top
        determinePerspective();

        //update entity animations
        for (Entity npc: npcs)
            npc.update(delta);

    }

    public void determinePerspective(){

        if (!perspectiveList.contains(GameManager.getInstance().getGameState().getPlayer()))
            perspectiveList.add(GameManager.getInstance().getGameState().getPlayer());

        //bubble sort
        for (int i = 1; i < perspectiveList.size(); i++){
            int j = i;
            while (j > 0 && perspectiveList.get(j).getX() < perspectiveList.get(j - 1).getX()) {

                MapObject temp = perspectiveList.get(j);

                perspectiveList.set(j, perspectiveList.get(j - 1));
                perspectiveList.set(j - 1, temp);

                j--;
            }
        }
    }

    public void enterRoom(){

        hasEntered = true;

        bgImage = ImageManager.makeBackgroundImage(LocationManager.getSurroundingLocations(this));

        bg_horizOffset = (Window.WINDOW_WIDTH - 3 * tileImage.getWidth()) / 2;
        bg_vertOffset = ((Window.WINDOW_HEIGHT - 3 * tileImage.getHeight()) / 2) - Tile.TILE_SIZE / 4;
        bg_x = bg_horizOffset;
        bg_y = bg_vertOffset;

        //exit loading screen
        GameManager.getInstance().getGameState().setInGameMenu(null);

    }

    public void inspect(){

        Player player = GameManager.getInstance().getGameState().getPlayer();

        int playerx = (int) (player.getX() + player.getWidth() / 2) / Tile.TILE_SIZE;
        int playery = (int) (player.getY() + player.getHeight() / 2) / Tile.TILE_SIZE;

        Tile inspectTile;

        if (player.isFacingUp())
            inspectTile = tilemap[playery - 1][playerx];

        else if (player.isFacingDown())
            inspectTile = tilemap[playery + 1][playerx];

        else if (player.isFacingRight())
            inspectTile = tilemap[playery][playerx + 1];

        else
            inspectTile = tilemap[playery][playerx - 1];

        Entity npc = inspectTile.getNpc();

        if (npc != null) {

            ObjectManager.onObjectEvent(npc);

        } else {

            PlaceableObject object = inspectTile.getObject();

            if (object != null) {
                inspectTile.getObject().onInspect();
                inspectTile.getObject().setOnInspect(ObjectEvent.NONE);
            }

        }

    }

    public boolean ableMoveUp(){
        Player player = GameManager.getInstance().getGameState().getPlayer();

        if (player.getY() / Tile.TILE_SIZE < 0.25 ||
                tilemap[(int) (player.getY() / Tile.TILE_SIZE)][(int) (player.getX() + DIST_CONST) / Tile.TILE_SIZE].isSolid() ||
                tilemap[(int) (player.getY() / Tile.TILE_SIZE)][(int) ((player.getX() + player.getWidth() - DIST_CONST) / Tile.TILE_SIZE)].isSolid())
            return false;

        return true;
    }

    public boolean ableMoveDown(){
        Player player = GameManager.getInstance().getGameState().getPlayer();

        if ((int) (player.getY() / Tile.TILE_SIZE) == tilemap.length - 1 ||
                tilemap[(int) ((player.getY() + player.getHeight()) / Tile.TILE_SIZE)][(int) ((player.getX() + DIST_CONST) / Tile.TILE_SIZE)].isSolid() ||
                tilemap[(int) ((player.getY() + player.getHeight()) / Tile.TILE_SIZE)][(int) ((player.getX() + player.getWidth() - DIST_CONST) / Tile.TILE_SIZE)].isSolid())
            return false;

        return true;
    }

    public boolean ableMoveLeft(){
        Player player = GameManager.getInstance().getGameState().getPlayer();

        if (player.getX() / Tile.TILE_SIZE < 0.25 ||
                tilemap[(int) ((player.getY() + DIST_CONST) / Tile.TILE_SIZE)][(int) (player.getX() / Tile.TILE_SIZE)].isSolid() ||
                tilemap[(int) ((player.getY() + player.getHeight() - DIST_CONST) / Tile.TILE_SIZE)][(int) (player.getX() / Tile.TILE_SIZE)].isSolid())
            return false;

        return true;
    }

    public boolean ableMoveRight(){
        Player player = GameManager.getInstance().getGameState().getPlayer();

        if ((int) (player.getX() / Tile.TILE_SIZE) == tilemap[0].length - 1 ||
                tilemap[(int) (player.getY() / Tile.TILE_SIZE)][(int) ((player.getX() + player.getWidth()) / Tile.TILE_SIZE)].isSolid() ||
                tilemap[(int) ((player.getY() + player.getHeight() - DIST_CONST) / Tile.TILE_SIZE)][(int) ((player.getX() + player.getWidth()) / Tile.TILE_SIZE)].isSolid())
            return false;

        return true;
    }

    public abstract void loadMap();

    public void render(Graphics2D g) {

        g.drawImage(bgImage, (int) bg_x, (int) bg_y, null);

        for (int i = 0; i < perspectiveList.size(); i++)
            perspectiveList.get(i).render(g);

        for (EnemyParty party: enemyParties)
            party.render(g);

    }
}