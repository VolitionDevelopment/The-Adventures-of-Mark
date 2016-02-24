package me.volition.location.tile;

import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class BrickWall extends Tile {

    private static BufferedImage image;

    public BrickWall(int x, int y) {
        super(loadImage(), true, x, y);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/me/volition/assets/image/tiles/brickwall_side.png");

        return image;
    }
}
