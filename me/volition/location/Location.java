package me.volition.location;

import me.volition.entity.Player;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.BattleManager;
import me.volition.util.GameManager;

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


    public Location(String name) {
        exits = new ArrayList<>();
        placeableObjects = new ArrayList<>();
        this.name = name;

        tilemap = loadMap();
    }

    /**
     * may be inefficient? idk check later xd
     * */
    public void update(Player player){
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

        //not colliding with any tiles that start battles
        if (tilemap[(int) (player.getY() + distConst) / Tile.TILE_SIZE][((int) player.getX() + player.getWidth()) / Tile.TILE_SIZE].startsBattle() ||
                tilemap[(int) (player.getY() + player.getHeight() - distConst) / Tile.TILE_SIZE][((int) player.getX() + player.getWidth()) / Tile.TILE_SIZE].startsBattle())
            BattleManager.startBattle(GameManager.getGameState(), player, tilemap[(int) player.getY() / Tile.TILE_SIZE][(int) (player.getX() + player.getWidth()) / Tile.TILE_SIZE].getEntities());

        else if (tilemap[(int) (player.getY() + distConst) / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].startsBattle() ||
                tilemap[((int) player.getY() + player.getHeight() - distConst) / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].startsBattle())
            BattleManager.startBattle(GameManager.getGameState(), player, tilemap[(int) player.getY() / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].getEntities());

        if (tilemap[((int) player.getY() + player.getHeight()) / Tile.TILE_SIZE][(int) (player.getX() + distConst) / Tile.TILE_SIZE].startsBattle() ||
                tilemap[((int) player.getY() + player.getHeight()) / Tile.TILE_SIZE][((int) player.getX() + player.getWidth() - distConst) / Tile.TILE_SIZE].startsBattle())
            BattleManager.startBattle(GameManager.getGameState(), player, tilemap[(int) (player.getY() + player.getHeight()) / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].getEntities());

        else if (tilemap[(int) player.getY() / Tile.TILE_SIZE][(int) (player.getX() + distConst) / Tile.TILE_SIZE].startsBattle() ||
                tilemap[(int) player.getY() / Tile.TILE_SIZE][((int) player.getX() + player.getWidth() - distConst) / Tile.TILE_SIZE].startsBattle())
            BattleManager.startBattle(GameManager.getGameState(), player, tilemap[(int) player.getY() / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].getEntities());

        for (Exit exit : exits) {
            if (exit.isActive() && exit.contains(player.getBounds())) {
                player.setLocation(exit.getLeadsTo());
            }
        }
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

    @Override
    public String toString() {
        return "Location{" +
                "name='" + name + '\'' +
                '}';
    }

    public void render(Graphics g) {
        for (Tile[] array: tilemap){
            for (Tile tile: array)
                tile.render(g);
        }

        for (PlaceableObject s: placeableObjects)
            s.render(g);

        for (Exit e: exits)
            g.drawRect(e.getBounds().x, e.getBounds().y, e.getBounds().width, e.getBounds().height);
    }
}