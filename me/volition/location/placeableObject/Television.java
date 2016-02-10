package me.volition.location.placeableObject;

import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/10/16.
 */
public class Television extends PlaceableObject {
    public Television(Tile[][] tileMap, double x, double y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/tv.png"), tileMap, true, x, y);
    }
}
