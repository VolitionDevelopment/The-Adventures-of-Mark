package me.volition.mapObject.placeableObject.impl.building;

import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;


/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Building_L2 extends PlaceableObject {

    private static BufferedImage image;

    public Building_L2(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "Funky building", "They probably spent, like, 5 minutes making this.", true, x, y);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/buildings/building_L_2.png");

        return image;
    }

}
