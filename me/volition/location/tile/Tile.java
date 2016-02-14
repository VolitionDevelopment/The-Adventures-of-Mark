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
    private ArrayList<Entity> entities;
    private PlaceableObject object;
    private Exit exit;

    public Tile(BufferedImage image, boolean isSolid, double x, double y){
        this.image = image;
        this.x = x;
        this.y = y;

        this.isSolid = isSolid;

        entities = null;
    }

    public void setObject(PlaceableObject object){
        this.object = object;
    }

    public PlaceableObject getObject(){
        return object;
    }

    public boolean isSolid(){
        return isSolid;
    }

    public ArrayList<Entity> getEntities(){
        return entities;
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

    public void setSolid(boolean isSolid){
        this.isSolid = isSolid;
    }

    public void setEntities(ArrayList<Entity> entities){
        this.entities = entities;
    }

    public void setExit(Exit exit){
        this.exit = exit;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public void render(Graphics g){
        if (image != null)
            g.drawImage(image, (int) x, (int) y, TILE_SIZE, TILE_SIZE, null);
    }
}
