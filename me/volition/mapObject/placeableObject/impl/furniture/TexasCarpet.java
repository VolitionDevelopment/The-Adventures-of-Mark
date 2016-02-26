package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.location.tile.Tile;
import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class TexasCarpet extends PlaceableObject {

    private static BufferedImage image;

    public TexasCarpet(int x, int y) {
        super(loadImage(), ObjectEvent.NONE, "Carpet of Texas", "Yee-haw!", false, x, y, 2 * Tile.TILE_SIZE, 2 * Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/texascarpet.png");

        return image;
    }
}
