package me.volition.mapObject.placeableObject.impl.building;

import me.volition.location.tile.Tile;
import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Building_Block_small extends PlaceableObject {

    private static BufferedImage image;

    public Building_Block_small(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "Suspiciously small building.", "It's a small building... ?", true, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_block_small.png");

        return image;
    }

}
