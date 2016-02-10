package me.volition.location;

import me.volition.Window;
import me.volition.entity.Player;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.BattleManager;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Demerzel on 2/3/16.
 */

public abstract class Location {
    private String name;
    private ArrayList<Exit> exits;
    private ArrayList<PlaceableObject> placeableObjects;
    private Tile[][] tilemap;
    private boolean freeCamera, safeRoom;


    public Location(String name, boolean safeRoom, boolean freeCamera) {
        exits = new ArrayList<>();
        placeableObjects = new ArrayList<>();
        this.name = name;
        this.freeCamera = freeCamera;
        this.safeRoom = safeRoom; //if false, random tiles can cause battles
        tilemap = loadMap();
    }

    /**
     * may be inefficient? idk check later xd
     * */
    public void update(Player player, double delta){

        int distConst = 10;

        //not colliding with any solid objects
        if (tilemap[(int) (player.getY() + distConst) / Tile.TILE_SIZE][((int) player.getX() + player.getWidth()) / Tile.TILE_SIZE].isSolid() ||
                tilemap[(int) (player.getY() + player.getHeight() - distConst) / Tile.TILE_SIZE][((int) player.getX() + player.getWidth()) / Tile.TILE_SIZE].isSolid())
            player.setGoingRight(false);

        else if (tilemap[(int) (player.getY() + distConst) / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].isSolid() ||
                tilemap[((int) player.getY() + player.getHeight() - distConst) / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].isSolid())
            player.setGoingLeft(false);

        if (tilemap[((int) player.getY() + player.getHeight()) / Tile.TILE_SIZE][(int) (player.getX() + distConst) / Tile.TILE_SIZE].isSolid() ||
                tilemap[((int) player.getY() + player.getHeight()) / Tile.TILE_SIZE][((int) player.getX() + player.getWidth() - distConst) / Tile.TILE_SIZE].isSolid())
            player.setGoingDown(false);

        else if (tilemap[(int) player.getY() / Tile.TILE_SIZE][(int) (player.getX() + distConst) / Tile.TILE_SIZE].isSolid() ||
                tilemap[(int) player.getY() / Tile.TILE_SIZE][((int) player.getX() + player.getWidth() - distConst) / Tile.TILE_SIZE].isSolid())
            player.setGoingUp(false);

        //start a random battle
        if (!safeRoom && player.isMoving()){
            if (Math.random() < 0.001 * delta) {
                player.stopMoving();
                BattleManager.startBattle(player, tilemap[(int) player.getY() / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].getImage());
            }
        }

        //start battle if walks over a battle tile
        if (tilemap[(int) player.getY() / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].startsBattle()) {
            player.stopMoving();
            player.setAnimator(player.getBattleAnimator());
            BattleManager.startBattle(player, tilemap[(int) player.getY() / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].getEntities(), tilemap[(int) player.getY() / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].getImage());
        }

        //check if at exit
        for (Exit exit : exits) {
            if (exit.isActive() && exit.contains(player.getBounds()))
                exit.enter(player);
        }

    }

    public void adjustCamera(double delta, Player player){
        //move objects if the player is moving
        if (player.isGoingDown()) {
            player.setY(player.getY() + (delta * player.getBaseSpeed()));
            for (Tile[] aTilemap : tilemap)
                for (Tile anATilemap : aTilemap)
                    anATilemap.setY(anATilemap.getY() - (delta * player.getBaseSpeed()));

            for (PlaceableObject placeableObject: placeableObjects)
                placeableObject.setY(placeableObject.getY() - (delta * player.getBaseSpeed()));

            for (Exit exit: exits)
                exit.setY(exit.getY() - (delta * player.getBaseSpeed()));

        } else if (player.isGoingUp()) {
            player.setY(player.getY() - (delta * player.getBaseSpeed()));
            for (Tile[] aTilemap : tilemap)
                for (Tile anATilemap : aTilemap)
                    anATilemap.setY(anATilemap.getY() + (delta * player.getBaseSpeed()));

            for (PlaceableObject placeableObject: placeableObjects)
                placeableObject.setY(placeableObject.getY() + (delta * player.getBaseSpeed()));

            for (Exit exit: exits)
                exit.setY(exit.getY() + (delta * player.getBaseSpeed()));
        }

        if (player.isGoingRight()){
            player.setX(player.getX() + (delta * player.getBaseSpeed()));
            for (Tile[] aTilemap : tilemap)
                for (Tile anATilemap : aTilemap)
                    anATilemap.setX(anATilemap.getX() - (delta * player.getBaseSpeed()));

            for (PlaceableObject placeableObject: placeableObjects)
                placeableObject.setX(placeableObject.getX() - (delta * player.getBaseSpeed()));

            for (Exit exit: exits)
                exit.setX(exit.getX() - (delta * player.getBaseSpeed()));

        } else if (player.isGoingLeft()){
            player.setX(player.getX() - (delta * player.getBaseSpeed()));
            for (Tile[] aTilemap : tilemap)
                for (Tile anATilemap : aTilemap)
                    anATilemap.setX(anATilemap.getX() + (delta * player.getBaseSpeed()));

            for (PlaceableObject placeableObject: placeableObjects)
                placeableObject.setX(placeableObject.getX() + (delta * player.getBaseSpeed()));

            for (Exit exit: exits)
                exit.setX(exit.getX() + (delta * player.getBaseSpeed()));
        }
    }

    public void enterRoom(Player player){
        loadExits(tilemap);
        if (freeCamera) {
            for (Tile[] aTilemap : tilemap) {
                for (Tile tile : aTilemap) {
                    tile.setX(tile.getX() + Window.WINDOW_WIDTH / 2 - (player.getX() + player.getWidth() / 2));
                    tile.setY(tile.getY() + Window.WINDOW_HEIGHT / 2 - (player.getY() + player.getHeight() / 2));
                }
            }

            for (PlaceableObject placeableObject : placeableObjects) {
                placeableObject.setX(placeableObject.getX() + Window.WINDOW_WIDTH / 2 - (player.getX() + player.getWidth() / 2));
                placeableObject.setY(placeableObject.getY() + Window.WINDOW_HEIGHT / 2 - (player.getY() + player.getHeight() / 2));
            }

            for (Exit exit : exits) {
                exit.setX(exit.getX() + Window.WINDOW_WIDTH / 2 - (player.getX() + player.getWidth() / 2));
                exit.setY(exit.getY() + Window.WINDOW_HEIGHT / 2 - (player.getY() + player.getHeight() / 2));
            }
        }
    }

    public boolean hasFreeCamera(){
        return freeCamera;
    }

    public ArrayList<Exit> getExits() {
        return exits;
    }

    public void setExits(ArrayList<Exit> exits) {
        this.exits = exits;
    }

    public void addExit(Exit exit){
        exits.add(exit);
    }

    public void setPlaceableObjects(ArrayList<PlaceableObject> placeableObjects){
        this.placeableObjects = placeableObjects;
    }

    public void addPlaceableObject(PlaceableObject placeableObject){
        placeableObjects.add(placeableObject);
    }

    public ArrayList<PlaceableObject> getPlaceableObjects(){
        return placeableObjects;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public abstract Tile[][] loadMap();

    public abstract void loadExits(Tile[][] tilemap);

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                '}';
    }

    public void render(Graphics g) {
        for (Tile[] array: tilemap){
            for (Tile tile: array)
                if (tile.getX() + Tile.TILE_SIZE > 0 && tile.getX() < Window.WINDOW_WIDTH && tile.getY() + Tile.TILE_SIZE > 0 && tile.getY() < Window.WINDOW_HEIGHT)
                    tile.render(g);
        }

        for (PlaceableObject s: placeableObjects)
            if (s.getX() + s.getWidth() > 0 && s.getX() < Window.WINDOW_WIDTH && s.getY() + s.getHeight() > 0 && s.getY() < Window.WINDOW_HEIGHT)
                s.render(g);

        g.setColor(Color.WHITE);
        for (Exit e: exits)
            if (e.getX() + e.getWidth() > 0 && e.getX() < Window.WINDOW_WIDTH && e.getY() + e.getHeight() > 0 && e.getY() < Window.WINDOW_HEIGHT)
                g.fillRect(e.getBounds().x, e.getBounds().y, e.getBounds().width, e.getBounds().height);
    }
}