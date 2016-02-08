package me.volition.location.placeableObject;

import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/8/16.
 */
public class Couch extends PlaceableObject {
    public Couch(Tile[][] tileMap, double x, double y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/couch.png"), tileMap, true, x, y);
    }
}
