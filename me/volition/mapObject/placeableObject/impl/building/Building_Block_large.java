package me.volition.mapObject.placeableObject.impl.building;

import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Building_Block_large extends PlaceableObject {
    public Building_Block_large(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_block_large.png"), ObjectEvent.NONE, "Suspiciously large.", "What could they possibly store in there?", true, x, y);
    }
}
