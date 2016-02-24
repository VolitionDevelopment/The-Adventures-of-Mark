package me.volition.mapObject.placeableObject.impl.furniture;

import me.volition.mapObject.ObjectEvent;
import me.volition.mapObject.placeableObject.PlaceableObject;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/10/16.
 */
public class Toilet extends PlaceableObject {

    private static BufferedImage image;

    public Toilet(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "The Porcelain Throne", "It's positioned in such a way that I can stare my lover in the eyes while I take a dump.", true, x, y);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/objects/furniture/toilet.png");

        return image;
    }
}
