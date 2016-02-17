package me.volition.location.placeableObject.impl.building;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/15/2016.
 */
public class Apartments extends PlaceableObject {
    public Apartments(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_1.png"), ObjectEvent.NONE, "My Apartment", "Older than your mom.", true, x, y);
    }
}
