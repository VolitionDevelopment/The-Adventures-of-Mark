package me.volition.location.placeableObject;

import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/11/16.
 */
public class LavaLamp extends PlaceableObject {
    public LavaLamp(Tile[][] tileMap, double x, double y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/lavalamp.png"), tileMap, true, x, y);
    }
}
