package me.volition.location;

import me.volition.Main;
import me.volition.Window;
import me.volition.location.tile.Tile;
import me.volition.mapObject.MapObject;
import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.entity.Entity;
import me.volition.mapObject.entity.Player;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.state.menu.ingamemenu.game.LoadMenu;
import me.volition.util.BattleManager;
import me.volition.util.GameManager;
import me.volition.util.ImageManager;
import me.volition.util.ObjectManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Demerzel on 2/3/16.
 */

public abstract class Location {

    private String name;
    private ArrayList<Exit> exits;
    private ArrayList<PlaceableObject> placeableObjects;
    private ArrayList<MapObject> perspectiveList;
    private Tile[][] tilemap;
    private BufferedImage bgImage;
    private double bg_x, bg_y, bg_horizOffset, bg_vertOffset;
    private boolean freeCamera, safeRoom;
    private ArrayList<Entity> npcs;

    private static final int DIST_CONST = 15; //for collision detection


    public Location(String name, boolean safeRoom, boolean freeCamera) {
        exits = new ArrayList<>();
        placeableObjects = new ArrayList<>();
        npcs = new ArrayList<>();

        perspectiveList = new ArrayList<>();

        this.name = name;
        this.freeCamera = freeCamera;
        this.safeRoom = safeRoom; //if false, random tiles can cause battles

        loadMap();
        this.bgImage = ImageManager.makeImageFromMap(this);

        perspectiveList.addAll(placeableObjects);
        perspectiveList.addAll(npcs);

    }

    public double getBg_horizOffset() {
        return bg_horizOffset;
    }

    public double getBg_vertOffset() {
        return bg_vertOffset;
    }

    public boolean hasFreeCamera(){
        return freeCamera;
    }

    public Tile[][] getTilemap(){
        return tilemap;
    }

    public void addExit(Exit exit){
        exits.add(exit);

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

        double x = npc.getX() / Tile.TILE_SIZE;
        double y = npc.getY() / Tile.TILE_SIZE;

        tilemap[(int) y][(int) x].setSolid(true);
        tilemap[(int) y][(int) x].setNpc(npc);
    }

    public void setTilemap(Tile[][] tilemap){
        this.tilemap = tilemap;
    }

    public void update(double delta){

        Player player = GameManager.getInstance().getGameState().getPlayer();

        Tile playerTile = tilemap[((int) player.getY() + player.getHeight() / 2) / Tile.TILE_SIZE][((int) player.getX() + player.getWidth() / 2) / Tile.TILE_SIZE];

        //random battles
        if (!safeRoom && player.isMoving()){
            if (Math.random() < Math.pow(delta, 2.5)) {

                player.stopMoving();

                player.setAnimator(player.getBattleAnimator());
                BattleManager.startBattle(player, playerTile.getImage());

            }
        }

        //battle tiles
        ArrayList<Entity> entities = playerTile.getBattleEntities();
        if (entities != null) {

            player.stopMoving();

            player.setAnimator(player.getBattleAnimator());
            BattleManager.startBattle(player, entities, playerTile.getImage());

            playerTile.setBattleEntities(null);

        }
        //check if at exit
        Exit exit = playerTile.getExit();
        if (exit != null && exit.isActive()) {

            player.stopMoving();

            GameManager.getInstance().getGameState().setInGameMenu(new LoadMenu());
            Main.getInstance().repaint();

            exit.enter(player);
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

        Player player = GameManager.getInstance().getGameState().getPlayer();

        loadExits(tilemap);

        //readjusts camera if its a free camera room
        if (freeCamera) {

            //center of player in render world
            double px = (Tile.TILE_SIZE / 2 * player.getX() / Tile.TILE_SIZE) - (Tile.TILE_SIZE / 2 * player.getY() / Tile.TILE_SIZE) + player.getWidth() / 2;
            double py = (Tile.TILE_SIZE / 4 * player.getX() / Tile.TILE_SIZE) + (Tile.TILE_SIZE / 4 * player.getY() / Tile.TILE_SIZE) + player.getHeight() / 2;

            double delta_x = - px / 2 - tilemap.length * Tile.TILE_SIZE / 2 + Window.WINDOW_WIDTH / 2;
            double delta_y = - py / 2 + Window.WINDOW_HEIGHT / 2;

            bg_x = delta_x;
            bg_y = delta_y;

            for (MapObject object: perspectiveList) {
                object.setX(object.getX() + delta_x);
                object.setY(object.getY() + delta_y);
            }

        } else {

            bg_horizOffset = (Window.WINDOW_WIDTH - bgImage.getWidth()) / 2;
            bg_vertOffset = ((Window.WINDOW_HEIGHT - bgImage.getHeight()) / 2) - Tile.TILE_SIZE / 4;
            bg_x += bg_horizOffset;
            bg_y += bg_vertOffset;

        }

        //exit loading screen
        GameManager.getInstance().getGameState().setInGameMenu(null);

    }

    public void adjustCamera(double delta){

        if (freeCamera) {

            Player player = GameManager.getInstance().getGameState().getPlayer();
            double dist = delta * player.getBaseSpeed();

            //move objects if the player is moving
            if (player.isGoingDown() && ableMoveDown()) {

                bg_y -= dist;

                for (Entity npc: npcs)
                    npc.setY(npc.getY() - dist);

                for (PlaceableObject object: placeableObjects)
                    object.setY(object.getY() - dist);

            } else if (player.isGoingUp() && ableMoveUp()) {

                bg_y += dist;

                for (Entity npc: npcs)
                    npc.setY(npc.getY() + dist);

                for (PlaceableObject object: placeableObjects)
                    object.setY(object.getY() + dist);
            }

            if (player.isGoingLeft() && ableMoveLeft()) {

                bg_x += dist;

                for (Entity npc: npcs)
                    npc.setX(npc.getX() + dist);

                for (PlaceableObject object: placeableObjects)
                    object.setX(object.getX() + dist);

            } else if (player.isGoingRight() && ableMoveRight()) {

                bg_x -= dist;

                for (Entity npc: npcs)
                    npc.setX(npc.getX() - dist);

                for (PlaceableObject object: placeableObjects)
                    object.setX(object.getX() - dist);

            }
        }

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

        if (tilemap[(int) (player.getY() / Tile.TILE_SIZE)][(int) (player.getX() + DIST_CONST) / Tile.TILE_SIZE].isSolid() ||
                tilemap[(int) (player.getY() / Tile.TILE_SIZE)][(int) ((player.getX() + player.getWidth() - DIST_CONST) / Tile.TILE_SIZE)].isSolid())
            return false;

        return true;
    }

    public boolean ableMoveDown(){
        Player player = GameManager.getInstance().getGameState().getPlayer();

        if (tilemap[(int) ((player.getY() + player.getHeight()) / Tile.TILE_SIZE)][(int) ((player.getX() + DIST_CONST) / Tile.TILE_SIZE)].isSolid() ||
                tilemap[(int) ((player.getY() + player.getHeight()) / Tile.TILE_SIZE)][(int) ((player.getX() + player.getWidth() - DIST_CONST) / Tile.TILE_SIZE)].isSolid())
            return false;

        return true;
    }

    public boolean ableMoveLeft(){
        Player player = GameManager.getInstance().getGameState().getPlayer();

        if (tilemap[(int) ((player.getY() + DIST_CONST) / Tile.TILE_SIZE)][(int) (player.getX() / Tile.TILE_SIZE)].isSolid() ||
                tilemap[(int) ((player.getY() + player.getHeight() - DIST_CONST) / Tile.TILE_SIZE)][(int) (player.getX() / Tile.TILE_SIZE)].isSolid())
            return false;

        return true;
    }

    public boolean ableMoveRight(){
        Player player = GameManager.getInstance().getGameState().getPlayer();

        if (tilemap[(int) (player.getY() / Tile.TILE_SIZE)][(int) ((player.getX() + player.getWidth()) / Tile.TILE_SIZE)].isSolid() ||
                tilemap[(int) ((player.getY() + player.getHeight() - DIST_CONST) / Tile.TILE_SIZE)][(int) ((player.getX() + player.getWidth()) / Tile.TILE_SIZE)].isSolid())
            return false;

        return true;
    }

    public abstract void loadMap();

    public abstract void loadExits(Tile[][] tilemap);

    public void render(Graphics2D g) {

        g.drawImage(bgImage, (int) bg_x, (int) bg_y, null);

        for (int i = 0; i < perspectiveList.size(); i++)
            perspectiveList.get(i).render(g);

    }
}