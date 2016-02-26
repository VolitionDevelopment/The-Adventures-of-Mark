package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.location.tile.Tile;
import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;


/**
 * Created by mccloskeybr on 2/13/16.
 */
public class Box extends PlaceableObject {

    private static BufferedImage image;

    public Box(double x, double y) {
        super(loadImage(), ObjectEvent.RANDOMUSABLE, "A Box", "It's an empty box.", true, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/chest.png");

        return image;
    }

}
