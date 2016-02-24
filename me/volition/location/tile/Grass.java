package me.volition.location.tile;

import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/15/2016.
 */
public class Grass extends Tile {

    private static BufferedImage image;

    public Grass(double x, double y) {
        super(loadImage(), false, x, y);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/tiles/grass.png");

        return image;
    }
}
