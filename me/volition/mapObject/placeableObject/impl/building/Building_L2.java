package me.volition.mapObject.placeableObject.impl.building;

import me.volition.mapObject.placeableObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Building_L2 extends PlaceableObject {
    public Building_L2(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_L_2.png"), ObjectEvent.NONE, "Funky building", "They probably spent, like, 5 minutes making this.", true, x, y);
    }
}
