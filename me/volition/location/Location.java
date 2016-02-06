package me.volition.location;

import me.volition.*;
import me.volition.Window;
import me.volition.entity.Entity;
import me.volition.entity.Player;
import me.volition.location.solidobject.SolidObject;
import me.volition.location.tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by Demerzel on 2/3/16.
 */

public abstract class Location {
    private String name;
    private ArrayList<Entity> entities;
    private ArrayList<Exit> exits;
    private ArrayList<SolidObject> solidObjects;
    private Tile[][] tilemap;


    public Location(String name) {
        entities = new ArrayList<>();
        exits = new ArrayList<>();
        solidObjects = new ArrayList<>();
        this.name = name;

        tilemap = loadMap();
    }

    public void update(Player player){
        //makes sure player isnt colliding with an object

        if (tilemap[(int) player.getY() / Tile.TILE_SIZE][((int) player.getX() + player.getWidth()) / Tile.TILE_SIZE].isSolid() ||
            tilemap[((int) player.getY() + player.getHeight()) / Tile.TILE_SIZE][((int) player.getX() + player.getWidth()) / Tile.TILE_SIZE].isSolid())
            player.setGoingRight(false);
        else if (tilemap[(int) player.getY() / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].isSolid() ||
                tilemap[((int) player.getY() + player.getHeight()) / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].isSolid())
            player.setGoingLeft(false);

        if (tilemap[((int) player.getY() + player.getHeight()) / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].isSolid() ||
                tilemap[((int) player.getY() + player.getHeight()) / Tile.TILE_SIZE][((int) player.getX() + player.getWidth()) / Tile.TILE_SIZE].isSolid())
            player.setGoingDown(false);
        else if (tilemap[(int) player.getY() / Tile.TILE_SIZE][(int) player.getX() / Tile.TILE_SIZE].isSolid() ||
                tilemap[(int) player.getY() / Tile.TILE_SIZE][((int) player.getX() + player.getWidth()) / Tile.TILE_SIZE].isSolid())
            player.setGoingUp(false);

        for (Exit exit: exits) {
            if (exit.isActive() && exit.contains(player.getBounds())){
                player.setLocation(exit.getLeadsTo());
            }
        }

        for (Entity e: entities){
            if (player.getX() == e.getX() && player.getY() == e.getY()){
                //start a battle xd
            }
        }
    }

    public void addEntity(Entity entity){
        entities.add(entity);
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }

    public void setEntities(ArrayList<Entity> entities) {
        this.entities = entities;
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

    public void setSolidObjects(ArrayList<SolidObject> solidObjects){
        this.solidObjects = solidObjects;
    }

    public void addSolidObject(SolidObject solidObject){
        solidObjects.add(solidObject);
    }

    public ArrayList<SolidObject> getSolidObjects(){
        return solidObjects;
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

        for (SolidObject s: solidObjects)
            s.render(g);

        for (Exit e: exits)
            g.drawRect(e.getBounds().x, e.getBounds().y, e.getBounds().width, e.getBounds().height);
    }
}