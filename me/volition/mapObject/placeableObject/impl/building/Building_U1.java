package me.volition.mapObject.placeableObject.impl.building;

import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Building_U1 extends PlaceableObject {
    public Building_U1(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_U_1.png"), ObjectEvent.NONE, "Weirdly shaped building", "Why is it shaped so weirdly?", true, x, y);
    }
}
