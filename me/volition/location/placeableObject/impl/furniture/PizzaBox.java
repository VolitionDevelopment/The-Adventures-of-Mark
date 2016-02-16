package me.volition.location.placeableObject.impl.furniture;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public class PizzaBox extends PlaceableObject {
    public PizzaBox(Tile[][] tilemap, int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/pizzabox.png"), tilemap, ObjectEvent.NONE, "Pizza box", "Freshman 50 here I come!", false, x, y);
    }
}
