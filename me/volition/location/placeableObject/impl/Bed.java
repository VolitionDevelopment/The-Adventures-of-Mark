package me.volition.location.placeableObject.impl;

import me.volition.location.placeableObject.ItemEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public class Bed extends PlaceableObject {
    public Bed(Tile[][] tilemap, int x, int y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/bed.png"), tilemap, ItemEvent.NONE, true, x, y);
    }

    public Bed(Tile[][] tilemap, ItemEvent event, int x, int y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/bed.png"), tilemap, event, true, x, y);
    }
}
