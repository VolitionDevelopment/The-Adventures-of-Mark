package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.mapObject.placeableObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/10/16.
 */
public class Toilet extends PlaceableObject {
    public Toilet(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/toilet.png"), ObjectEvent.NONE, "The Porcelain Throne", "It's positioned in such a way that I can stare my lover in the eyes while I take a dump.", true, x, y);
    }
}
