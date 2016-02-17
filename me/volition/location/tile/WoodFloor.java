package me.volition.location.tile;

import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/5/16.
 */
public class WoodFloor extends Tile {
    public WoodFloor(int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/tiles/woodfloor.png"), false, x, y);
    }
}
