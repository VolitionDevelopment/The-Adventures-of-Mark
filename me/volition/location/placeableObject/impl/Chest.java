package me.volition.location.placeableObject.impl;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/13/16.
 */
public class Chest extends PlaceableObject {
    public Chest(Tile[][] tileMap, double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/chest.png"), tileMap, ObjectEvent.RANDOMITEM, true, x, y);
    }

    public Chest(Tile[][] tileMap, ObjectEvent event, double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/chest.png"), tileMap, event, true, x, y);
    }
}
