package me.volition.mapObject.placeableObject.impl.building;

import me.volition.location.tile.Tile;
import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Building_L3 extends PlaceableObject {

    private static BufferedImage image;

    public Building_L3(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "The Amazing Party Building", "With a name like that, it can't possibly be boring!", true, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_L_3.png");

        return image;
    }

}
