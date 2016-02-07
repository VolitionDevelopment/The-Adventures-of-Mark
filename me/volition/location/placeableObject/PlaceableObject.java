package me.volition.location.placeableObject;

import me.volition.entity.Entity;
import me.volition.entity.enemies.Fratkid;
import me.volition.entity.enemies.Jalapeno;
import me.volition.entity.enemies.Stoner;
import me.volition.location.tile.Tile;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public abstract class PlaceableObject {

    private BufferedImage image;
    private int x, y;

    //solid objects
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

    //battle tiles
    //can leave entities null to have a random pool of entities
    public PlaceableObject(BufferedImage image, Tile[][] tileMap, ArrayList<Entity> entities, int x, int y) { //location, size in terms of TILES, not pixels
        this.image = image;
        this.x = x;
        this.y = y;

        if (entities == null) {
            Random random = new Random();
            entities = new ArrayList<>();
            int size = random.nextInt(3) + 1;
            for (int i = 0; i < size; i++) {
                int mob = random.nextInt(3);
                if (mob == 0)
                    entities.add(new Fratkid());
                else if (mob == 1)
                    entities.add(new Jalapeno());
                else
                    entities.add(new Stoner());
            }

        }

        tileMap[y][x].setStartsBattle(true);
        tileMap[y][x].setEntities(entities);
    }

    public Rectangle getBounds(){
        return new Rectangle(x, y, image.getWidth(), image.getHeight());
    }

    public void render(Graphics g){
        g.drawImage(image, x * Tile.TILE_SIZE, y * Tile.TILE_SIZE, null);
    }
}
