package me.volition.location.placeableObject.impl;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public class Bed extends PlaceableObject {
    public Bed(Tile[][] tilemap, int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/bed.png"), tilemap, ObjectEvent.NONE, true, x, y);
    }

    public Bed(Tile[][] tilemap, ObjectEvent event, int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/bed.png"), tilemap, event, true, x, y);
    }
}
