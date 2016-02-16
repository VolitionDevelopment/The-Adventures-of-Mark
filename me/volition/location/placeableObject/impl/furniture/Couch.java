package me.volition.location.placeableObject.impl.furniture;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/8/16.
 */
public class Couch extends PlaceableObject {
    public Couch(Tile[][] tileMap, double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/couch.png"), tileMap, ObjectEvent.NONE, "Couch", "I once gave birth on this beauty.", true, x, y);
    }
}
