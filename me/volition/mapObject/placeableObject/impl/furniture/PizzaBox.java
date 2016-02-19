package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public class PizzaBox extends PlaceableObject {
    public PizzaBox(int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/pizzabox.png"), ObjectEvent.NONE, "Pizza box", "Freshman 50 here I come!", false, x, y);
    }
}
