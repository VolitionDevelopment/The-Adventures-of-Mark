package me.volition.mapObject.placeableObject.impl.building;

import me.volition.location.tile.Tile;
import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/15/16.
 */
public class Shed_normal extends PlaceableObject {

    private static BufferedImage image;

    public Shed_normal(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "Shed", "A some-what large wooden person container.", true, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/shed_1.png");

        return image;
    }

}
