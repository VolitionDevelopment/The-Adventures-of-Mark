package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.location.tile.Tile;
import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;


/**
 * Created by mccloskeybr on 2/11/16.
 */
public class LavaLamp extends PlaceableObject {

    private static BufferedImage image;

    public LavaLamp(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "A Lava-Lamp", "What is this, the 70s?", true, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, 2 * Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/lavalamp.png");

        return image;
    }

}
