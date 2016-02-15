package me.volition.location.tile;

import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/15/2016.
 */
public class Grass extends Tile {
    public Grass(double x, double y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/tiles/grass.png"), false, x, y);
    }
}
