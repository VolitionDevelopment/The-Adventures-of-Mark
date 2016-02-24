package me.volition.location.tile;

import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;


/**
 * Created by mccloskeybr on 2/5/16.
 */
public class WoodFloor extends Tile {

    private static BufferedImage image;

    public WoodFloor(int x, int y) {
        super(loadImage(), false, x, y);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/tiles/woodfloor.png");

        return image;
    }
}
