package me.volition.location.tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public abstract class Tile {

    public static final int TILE_SIZE = 64;

    private BufferedImage image;
    private int x, y;
    private boolean isSolid;

    public Tile(BufferedImage image, boolean isSolid, int x, int y){
        this.image = image;
        this.x = x;
        this.y = y;
        this.isSolid = isSolid;
    }

    public void setSolid(boolean isSolid){
        this.isSolid = isSolid;
    }

    public boolean isSolid(){
        return isSolid;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public void render(Graphics g){
        g.drawImage(image, x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE, null);
    }
}
