package me.volition.mapObject.placeableObject;

import me.volition.mapObject.MapObject;
import me.volition.mapObject.entity.Entity;
import me.volition.mapObject.entity.Player;
import me.volition.mapObject.entity.enemies.Chili;
import me.volition.mapObject.entity.enemies.Fratkid;
import me.volition.mapObject.entity.enemies.Stoner;
import me.volition.location.tile.Tile;
import me.volition.util.ItemManager;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public abstract class PlaceableObject extends MapObject{

    private BufferedImage image;
    private String name, desc;
    private int width, height;
    private boolean isSolid;

    //solid objects
    public PlaceableObject(BufferedImage image, ObjectEvent event, String name, String desc, boolean isSolid, double x, double y) { //location, size in terms of TILES, not pixels
        super(x, y, event, name, desc);

        this.image = image;
        this.width = image.getWidth();
        this.height = image.getHeight();
        this.name = name;
        this.desc = desc;
        this.isSolid = isSolid;
    }

    //battle tiles
    //can leave entities null to have a random pool of entities
    public PlaceableObject(Tile[][] tileMap, ArrayList<Entity> entities, double x, double y) { //location, size in terms of TILES, not pixels
        super(-1, -1, ObjectEvent.NONE, "", "");

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

        tileMap[(int) y / Tile.TILE_SIZE][(int) x / Tile.TILE_SIZE].setBattleEntities(entities);
    }

    public String getName(){
        return name;
    }

    public String getDesc(){
        return desc;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public boolean isSolid(){
        return isSolid;
    }

    public BufferedImage getImage(){
        return image;
    }

    public void onInspect(){
        ItemManager.onObjectEvent(this);
    }

}
