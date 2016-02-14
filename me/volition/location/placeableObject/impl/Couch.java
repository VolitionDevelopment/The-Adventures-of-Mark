package me.volition.location.placeableObject.impl;

import me.volition.location.placeableObject.ItemEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/8/16.
 */
public class Couch extends PlaceableObject {
    public Couch(Tile[][] tileMap, double x, double y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/couch.png"), tileMap, ItemEvent.NONE, true, x, y);
    }

    public Couch(Tile[][] tileMap, ItemEvent event, double x, double y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/couch.png"), tileMap, event, true, x, y);
    }
}
