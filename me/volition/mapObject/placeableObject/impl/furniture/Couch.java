package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/8/16.
 */
public class Couch extends PlaceableObject {

    private static BufferedImage image;

    public Couch(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "Couch", "I once gave birth on this beauty.", true, x, y);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/couch.png");

        return image;
    }

}
