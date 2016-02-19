package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.mapObject.placeableObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public class Bed extends PlaceableObject {
    public Bed(int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/bed.png"), ObjectEvent.NONE, "A Bed", "A place to whip one out real quick.", true, x, y);
    }
}
