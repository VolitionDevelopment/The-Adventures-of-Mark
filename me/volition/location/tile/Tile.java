package me.volition.location.tile;

import me.volition.entity.Entity;
import me.volition.location.Exit;
import me.volition.location.placeableObject.PlaceableObject;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public abstract class Tile {

    public static final int TILE_SIZE = 64;

    private BufferedImage image;
    private double x, y;
    private boolean isSolid;
    private ArrayList<Entity> battleEntities;
    private PlaceableObject object;
    private Entity npc;
    private Exit exit;

    public Tile(BufferedImage image, boolean isSolid, double x, double y){
        this.image = image;
        this.x = x;
        this.y = y;

        this.isSolid = isSolid;

        battleEntities = null;
    }

    public PlaceableObject getObject(){
        return object;
    }

    public Entity getNpc(){
        return npc;
    }

    public boolean isSolid(){
        return isSolid;
    }

    public ArrayList<Entity> getBattleEntities(){
        return battleEntities;
    }

    public Exit getExit(){
        return exit;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public BufferedImage getImage(){
        return image;
    }

    public void setObject(PlaceableObject object){
        this.object = object;
    }

    public void setNpc(Entity entity){
        this.npc = entity;
    }

    public void setSolid(boolean isSolid){
        this.isSolid = isSolid;
    }

    public void setBattleEntities(ArrayList<Entity> battleEntities){
        this.battleEntities = battleEntities;
    }

    public void setExit(Exit exit){
        this.exit = exit;
    }

}
