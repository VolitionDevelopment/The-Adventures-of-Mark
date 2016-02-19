package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/10/16.
 */
public class Wardrobe extends PlaceableObject {
    public Wardrobe(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/wardrobe.png"), ObjectEvent.NONE, "Wardrobe", "I store my underwear here.", true, x, y);
    }
}
