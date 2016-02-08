package me.volition.location.tile;

import me.volition.entity.Entity;

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
    private boolean startsBattle;
    private ArrayList<Entity> entities;

    public Tile(BufferedImage image, boolean isSolid, double x, double y){
        this.image = image;
        this.x = x;
        this.y = y;

        this.isSolid = isSolid;

        startsBattle = false;
        entities = null;
    }

    public void setSolid(boolean isSolid){
        this.isSolid = isSolid;
    }

    public boolean isSolid(){
        return isSolid;
    }

    public void setStartsBattle(boolean startsBattle){
        this.startsBattle = startsBattle;
    }

    public boolean startsBattle(){
        return startsBattle;
    }

    public void setEntities(ArrayList<Entity> entities){
        this.entities = entities;
    }

    public ArrayList<Entity> getEntities(){
        return entities;
    }

    public void setX(double x){
        this.x = x;
    }

    public void setY(double y){
        this.y = y;
    }

    public double getX(){
        return x;
    }

    public double getY(){
        return y;
    }

    public void render(Graphics g){

        if (image != null)
            g.drawImage(image, (int) x, (int) y, TILE_SIZE, TILE_SIZE, null);
    }
}
