package me.volition.location.tile;

import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/15/2016.
 */
public class BrickWall_Side extends Tile {
    public BrickWall_Side(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/tiles/brickwall_side.png"), false, x, y);
    }
}
