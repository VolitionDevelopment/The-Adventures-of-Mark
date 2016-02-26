package me.volition.mapObject.placeableObject.impl.building;

import me.volition.location.tile.Tile;
import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/15/2016.
 */
public class Apartments extends PlaceableObject {

    private static BufferedImage image;

    public Apartments(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "My Apartment", "Older than your mom.", true, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE ,Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_1.png");

        return image;
    }
}
