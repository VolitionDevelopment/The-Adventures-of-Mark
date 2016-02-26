package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.location.tile.Tile;
import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;


/**
 * Created by mccloskeybr on 2/10/16.
 */
public class Television extends PlaceableObject {

    private static BufferedImage image;

    public Television(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "A good ol' Television", "Your least favorite show is on.", true, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, 2 * Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/tv.png");

        return image;
    }

}
