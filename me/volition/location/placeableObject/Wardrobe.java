package me.volition.location.placeableObject;

import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/10/16.
 */
public class Wardrobe extends PlaceableObject {
    public Wardrobe(Tile[][] tileMap, double x, double y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/wardrobe.png"), tileMap, true, x, y);
    }
}
