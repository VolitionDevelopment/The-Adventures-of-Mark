package me.volition.location.placeableObject.impl.building;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/15/16.
 */
public class Shed_tall extends PlaceableObject {
    public Shed_tall(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/shed_2.png"), ObjectEvent.NONE, "Shed", "A some-what large(r) wooden person container.", true, x, y);
    }
}
