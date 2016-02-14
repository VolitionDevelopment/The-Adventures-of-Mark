package me.volition.location.placeableObject.impl;

import me.volition.location.placeableObject.ItemEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class Desk extends PlaceableObject {
    public Desk(Tile[][] tileMap, int x, int y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/desk.png"), tileMap, ItemEvent.NONE, true, x, y);
    }

    public Desk(Tile[][] tileMap, ItemEvent event, int x, int y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/desk.png"), tileMap, event, true, x, y);
    }
}
