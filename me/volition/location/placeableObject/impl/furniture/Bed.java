package me.volition.location.placeableObject.impl.furniture;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public class Bed extends PlaceableObject {
    public Bed(Tile[][] tilemap, int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/bed.png"), tilemap, ObjectEvent.NONE, "A Bed", "A place to whip one out real quick.", true, x, y);
    }
}
