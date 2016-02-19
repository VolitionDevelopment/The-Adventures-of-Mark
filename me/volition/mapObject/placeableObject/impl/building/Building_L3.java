package me.volition.mapObject.placeableObject.impl.building;

import me.volition.mapObject.placeableObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Building_L3 extends PlaceableObject {
    public Building_L3(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_L_3.png"), ObjectEvent.NONE, "The Amazing Party Building", "With a name like that, it can't possibly be boring!", true, x, y);
    }
}
