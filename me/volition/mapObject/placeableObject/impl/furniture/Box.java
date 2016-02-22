package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/13/16.
 */
public class Box extends PlaceableObject {
    public Box(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/chest.png"), ObjectEvent.RANDOMUSABLE, "A Box", "It's an empty box.", true, x, y);
    }
}
