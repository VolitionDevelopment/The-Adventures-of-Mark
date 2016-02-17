package me.volition.location.placeableObject.impl.building;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/15/16.
 */
public class Building_L1 extends PlaceableObject {
    public Building_L1(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_L_1.png"), ObjectEvent.NONE, "building_1", "The dev who created this building probably forgot about naming it.", true, x, y);
    }
}
