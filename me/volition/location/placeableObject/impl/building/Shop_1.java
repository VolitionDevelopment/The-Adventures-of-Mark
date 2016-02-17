package me.volition.location.placeableObject.impl.building;

import me.volition.location.placeableObject.ObjectEvent;
import me.volition.location.placeableObject.PlaceableObject;
import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Shop_1 extends PlaceableObject {
    public Shop_1(Tile[][] tileMap, double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/shop_1.png"), tileMap, ObjectEvent.NONE, "Shop", "Can buy things here.", true, x, y);
    }
}
