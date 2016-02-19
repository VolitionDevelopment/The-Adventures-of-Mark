package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.mapObject.placeableObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class TexasCarpet extends PlaceableObject {
    public TexasCarpet(int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/texascarpet.png"), ObjectEvent.NONE, "Carpet of Texas", "Yee-haw!", false, x, y);
    }
}
