package me.volition.mapObject.placeableObject.impl.building;

import me.volition.mapObject.placeableObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Building_Long1 extends PlaceableObject{

    public Building_Long1(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_long_1.png"), ObjectEvent.NONE, "A long building", "Not longer than my schlong.", true, x, y);
    }
}
