package me.volition.location.placeableObject.impl.building;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Building_L2 extends PlaceableObject {
    public Building_L2(Tile[][] tileMap, double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_L_2.png"), tileMap, ObjectEvent.NONE, "Funky building", "They probably spent, like, 5 minutes making this.", true, x, y);
    }
}
