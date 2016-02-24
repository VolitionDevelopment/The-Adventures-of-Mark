package me.volition.mapObject.placeableObject.impl.building;

import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Building_Long1 extends PlaceableObject{

    private static BufferedImage image;

    public Building_Long1(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "A long building", "Not longer than my schlong.", true, x, y);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_long_1.png");

        return image;
    }

}
