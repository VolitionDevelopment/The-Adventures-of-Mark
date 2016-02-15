package me.volition.location.tile;

import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/15/2016.
 */
public class Street extends Tile {
    public Street(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/tiles/street.png"), false, x, y);
    }
}
