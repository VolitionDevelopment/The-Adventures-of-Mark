package volition.adv_of_mark.mapObject.placeableObject;

import volition.adv_of_mark.location.Location;
import volition.adv_of_mark.mapObject.MapObject;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.mapObject.entity.enemies.Chili;
import volition.adv_of_mark.mapObject.entity.enemies.Fratkid;
import volition.adv_of_mark.mapObject.entity.enemies.Stoner;
import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.util.GameManager;
import volition.adv_of_mark.util.ObjectManager;

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

    public void render(Graphics2D g, int x, int y){
        g.drawImage(image, x, y, null);
    }

}
