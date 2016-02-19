package me.volition.mapObject.placeableObject.impl.building;

import me.volition.mapObject.placeableObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Building_Block_small extends PlaceableObject {
    public Building_Block_small(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_block_small.png"), ObjectEvent.NONE, "Suspiciously small building.", "It's a small building... ?", true, x, y);
    }
}
