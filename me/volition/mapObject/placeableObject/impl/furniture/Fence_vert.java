package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Fence_vert extends PlaceableObject {
    public Fence_vert(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/fence_vert.png"), ObjectEvent.NONE, "Fence", "It's a fence.", true, x, y);
    }
}
