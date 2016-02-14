package me.volition.location.placeableObject;

import me.volition.entity.Entity;
import me.volition.entity.Player;
import me.volition.entity.enemies.Chili;
import me.volition.entity.enemies.Fratkid;
import me.volition.entity.enemies.Stoner;
import me.volition.location.tile.Tile;
import me.volition.util.ItemManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public abstract class PlaceableObject {

    private BufferedImage image;
    private double x, y;
    private int width, height;
    private ObjectEvent event;

    //solid objects
    public PlaceableObject(BufferedImage image, Tile[][] tileMap, ObjectEvent event, boolean isSolid, double x, double y) { //location, size in terms of TILES, not pixels
        this.image = image;
        this.event = event;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.x = x;
        this.y = y;

        x /= Tile.TILE_SIZE;
        y /= Tile.TILE_SIZE;

        if (isSolid){
            int width = image.getWidth() / Tile.TILE_SIZE;
            int height = image.getHeight() / Tile.TILE_SIZE;

            for (int i = 0; i < height; i++) {
                for (int j = 0; j < width; j++) {
                    if (y + i < tileMap.length && x + j < tileMap[i].length) {
                        tileMap[(int) y + i][(int) x + j].setSolid(true);
                        tileMap[(int) y + i][(int) x + j].setObject(this);
                    }
                }
            }
        }
    }

    //battle tiles
    //can leave entities null to have a random pool of entities
    public PlaceableObject(Tile[][] tileMap, ArrayList<Entity> entities, double x, double y) { //location, size in terms of TILES, not pixels
        event = ObjectEvent.NONE;

        if (entities == null) {
            Random random = new Random();
            entities = new ArrayList<>();
            int size = random.nextInt(3) + 1;
            for (int i = 0; i < size; i++) {
                int mob = random.nextInt(3);
                if (mob == 0)
                    entities.add(new Fratkid());
                else if (mob == 1)
                    entities.add(new Chili());
                else
                    entities.add(new Stoner());
            }

        }

        tileMap[(int) y / Tile.TILE_SIZE][(int) x / Tile.TILE_SIZE].setStartsBattle(true);
        tileMap[(int) y / Tile.TILE_SIZE][(int) x / Tile.TILE_SIZE].setEntities(entities);
    }

    public void onInspect(Player player){
        ItemManager.onObjectEvent(player, event);
    }

    public void setEvent(ObjectEvent event){
        this.event = event;
    }

    public void setX(double x) {
        this.x = x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Rectangle getBounds(){
        return new Rectangle((int) x, (int) y, image.getWidth(), image.getHeight());
    }

    public void render(Graphics g){
        if (image != null)
            g.drawImage(image, (int) x, (int) y, null);
    }
}
