package me.volition.location.placeableObject.impl;

import me.volition.location.placeableObject.ItemEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/10/16.
 */
public class Television extends PlaceableObject {
    public Television(Tile[][] tileMap, double x, double y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/tv.png"), tileMap, ItemEvent.NONE, true, x, y);
    }

    public Television(Tile[][] tileMap, ItemEvent event, double x, double y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/tv.png"), tileMap, event, true, x, y);
    }
}
