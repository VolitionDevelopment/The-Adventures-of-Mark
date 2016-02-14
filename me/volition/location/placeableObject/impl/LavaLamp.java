package me.volition.location.placeableObject.impl;

import me.volition.location.placeableObject.ItemEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/11/16.
 */
public class LavaLamp extends PlaceableObject {
    public LavaLamp(Tile[][] tileMap, double x, double y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/lavalamp.png"), tileMap, ItemEvent.NONE, true, x, y);
    }

    public LavaLamp(Tile[][] tileMap, ItemEvent event, double x, double y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/lavalamp.png"), tileMap, event, true, x, y);
    }
}
