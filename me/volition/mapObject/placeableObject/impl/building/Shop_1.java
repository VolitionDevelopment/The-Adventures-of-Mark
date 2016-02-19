package me.volition.mapObject.placeableObject.impl.building;

import me.volition.mapObject.placeableObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Shop_1 extends PlaceableObject {
    public Shop_1(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/shop_1.png"), ObjectEvent.NONE, "Shop", "Can buy things here.", true, x, y);
    }
}
