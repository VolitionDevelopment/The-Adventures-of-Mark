package me.volition.location.placeableObject;

import me.volition.location.tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public abstract class PlaceableObject {

    private BufferedImage image;
    private int x, y;

    public PlaceableObject(BufferedImage image, Tile[][] tileMap, boolean isSolid, int x, int y) { //location, size in terms of TILES, not pixels
        this.image = image;
        this.x = x;
        this.y = y;

        if (isSolid){
            int width = image.getWidth() / Tile.TILE_SIZE;
            int height = image.getHeight() / Tile.TILE_SIZE;

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (y + i < tileMap.length && x + j < tileMap[i].length)
                        tileMap[y + i][x + j].setSolid(true);
                }
            }
        }
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public void render(Graphics g){
        g.drawImage(image, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, null);
    }
}
