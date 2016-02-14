package me.volition.location.placeableObject.impl;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/11/16.
 */
public class LavaLamp extends PlaceableObject {
    public LavaLamp(Tile[][] tileMap, double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/lavalamp.png"), tileMap, ObjectEvent.NONE, true, x, y);
    }

    public LavaLamp(Tile[][] tileMap, ObjectEvent event, double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/lavalamp.png"), tileMap, event, true, x, y);
    }
}
