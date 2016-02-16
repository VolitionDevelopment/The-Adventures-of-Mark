package me.volition.location.placeableObject.impl.furniture;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/11/16.
 */
public class LavaLamp extends PlaceableObject {
    public LavaLamp(Tile[][] tileMap, double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/lavalamp.png"), tileMap, ObjectEvent.NONE, "A Lava-Lamp", "What is this, the 70s?", true, x, y);
    }
}
