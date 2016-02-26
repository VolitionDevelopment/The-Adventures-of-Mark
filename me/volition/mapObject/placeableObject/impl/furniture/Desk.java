package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.location.tile.Tile;
import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class Desk extends PlaceableObject {

    private static BufferedImage image;

    public Desk(int x, int y) {
        super(loadImage(), ObjectEvent.NONE, "A Desk", "9001 nights spent bullshitting essays.", true, x, y, Tile.TILE_SIZE, 2 * Tile.TILE_SIZE, 2 * Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/desk.png");

        return image;
    }
}
