package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.location.Location;
import me.volition.location.tile.Tile;
import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/10/16.
 */
public class Wardrobe extends PlaceableObject {

    private static BufferedImage image;

    public Wardrobe(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "Wardrobe", "I store my underwear here.", true, x, y, Tile.TILE_SIZE, 2 * Tile.TILE_SIZE, 2 * Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/wardrobe.png");

        return image;
    }
}
