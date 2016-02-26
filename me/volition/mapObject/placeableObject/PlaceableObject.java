package me.volition.mapObject.placeableObject;

import me.volition.location.Location;
import me.volition.mapObject.MapObject;
import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.entity.Entity;
import me.volition.mapObject.entity.enemies.Chili;
import me.volition.mapObject.entity.enemies.Fratkid;
import me.volition.mapObject.entity.enemies.Stoner;
import me.volition.location.tile.Tile;
import me.volition.util.GameManager;
import me.volition.util.ObjectManager;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public abstract class PlaceableObject extends MapObject{

    private BufferedImage image;
    private boolean isSolid;

    //solid objects
    public PlaceableObject(BufferedImage image, ObjectEvent event, String name, String desc, boolean isSolid, double x, double y, int length, int width, int height) { //location, size in terms of TILES, not pixels
        super(x, y, length, width, height, event, name, desc);

        this.image = image;
        this.isSolid = isSolid;
    }

    //battle tiles
    //can leave entities null to have a random pool of entities
    public PlaceableObject(Tile[][] tileMap, ArrayList<Entity> entities, double x, double y) { //location, size in terms of TILES, not pixels
        super(-1, -1, -1, -1, -1, ObjectEvent.NONE, "", "");

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

    public boolean isSolid(){
        return isSolid;
    }

    public BufferedImage getImage(){
        return image;
    }

    public void onInspect(){
        ObjectManager.onObjectEvent(this);
    }

    public void render(Graphics2D g){

        if (image != null) {
            Location location = GameManager.getInstance().getGameState().getCurrentLocation();

            if (location.hasFreeCamera()) {

                g.drawImage(
                        image,
                        (int) getX(),
                        (int) getY(),
                        null
                );

            } else {

                g.drawImage(
                        image,
                        (int) location.getBg_horizOffset() + (location.getTilemap().length * Tile.TILE_SIZE / 2)
                                + (int) ((Tile.TILE_SIZE / 2) * (getX() / Tile.TILE_SIZE) - (Tile.TILE_SIZE / 2) * (getY() / Tile.TILE_SIZE))
                                - Tile.TILE_SIZE / 2,
                        (int) location.getBg_vertOffset()
                                + (int) ((Tile.TILE_SIZE / 4) * (getX() / Tile.TILE_SIZE) + (Tile.TILE_SIZE / 4) * (getY() / Tile.TILE_SIZE))
                                - image.getHeight() / 2 * ((image.getHeight() / Tile.TILE_SIZE) - 1)
                                + Tile.TILE_SIZE / 4,
                        null
                );

            }
        }

    }

}
