package me.volition.location.placeableObject.impl.building;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Building_Block_small extends PlaceableObject {
    public Building_Block_small(Tile[][] tileMap, double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_block_small.png"), tileMap, ObjectEvent.NONE, "Suspiciously small building.", "It's a small building... ?", true, x, y);
    }
}
