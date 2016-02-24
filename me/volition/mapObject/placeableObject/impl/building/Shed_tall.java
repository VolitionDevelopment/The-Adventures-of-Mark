package me.volition.mapObject.placeableObject.impl.building;

import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/15/16.
 */
public class Shed_tall extends PlaceableObject {

    private static BufferedImage image;

    public Shed_tall(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "Shed", "A some-what large(r) wooden person container.", true, x, y);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/shed_2.png");

        return image;
    }

}
