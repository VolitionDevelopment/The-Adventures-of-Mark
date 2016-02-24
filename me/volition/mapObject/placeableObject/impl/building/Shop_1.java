package me.volition.mapObject.placeableObject.impl.building;

import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Shop_1 extends PlaceableObject {

    private static BufferedImage image;

    public Shop_1(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "Shop", "Can buy things here.", true, x, y);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/shop_1.png");

        return image;
    }
}
