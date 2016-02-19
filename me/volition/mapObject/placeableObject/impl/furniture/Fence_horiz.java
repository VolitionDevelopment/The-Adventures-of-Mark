package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Fence_horiz extends PlaceableObject {
    public Fence_horiz(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/fence_horiz.png"), ObjectEvent.NONE, "Fence", "It's a fence.", true, x, y);
    }
}
