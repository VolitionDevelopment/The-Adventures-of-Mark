package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.location.tile.Tile;
import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public class Bed extends PlaceableObject {

    private static BufferedImage image;

    public Bed(int x, int y) {
        super(loadImage(), ObjectEvent.NONE, "A Bed", "A place to whip one out real quick.", true, x, y, Tile.TILE_SIZE, 2 * Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/bed.png");

        return image;
    }
}
