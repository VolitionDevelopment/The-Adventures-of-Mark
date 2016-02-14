package me.volition.location.placeableObject.impl;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/10/16.
 */
public class Wardrobe extends PlaceableObject {
    public Wardrobe(Tile[][] tileMap, double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/wardrobe.png"), tileMap, ObjectEvent.NONE, true, x, y);
    }

    public Wardrobe(Tile[][] tileMap, ObjectEvent event, double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/wardrobe.png"), tileMap, event, true, x, y);
    }
}
