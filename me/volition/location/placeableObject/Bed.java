package me.volition.location.placeableObject;

import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public class Bed extends PlaceableObject {
    public Bed(Tile[][] tilemap, int x, int y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/bed.png"), tilemap, true, x, y);
    }
}