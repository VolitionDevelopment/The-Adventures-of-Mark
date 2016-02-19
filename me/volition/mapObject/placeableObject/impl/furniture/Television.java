package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.mapObject.placeableObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/10/16.
 */
public class Television extends PlaceableObject {
    public Television(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/tv.png"), ObjectEvent.NONE, "A good ol' Television", "Your least favorite show is on.", true, x, y);
    }
}
