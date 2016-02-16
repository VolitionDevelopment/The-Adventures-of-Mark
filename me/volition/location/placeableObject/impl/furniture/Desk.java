package me.volition.location.placeableObject.impl.furniture;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class Desk extends PlaceableObject {
    public Desk(Tile[][] tileMap, int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/desk.png"), tileMap, ObjectEvent.NONE, "A Desk", "9001 nights spent bullshitting essays.", true, x, y);
    }
}
