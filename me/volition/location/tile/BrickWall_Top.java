package me.volition.location.tile;

import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class BrickWall_Top extends Tile {
    public BrickWall_Top(int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/tiles/brickwall_top.png"), true, x, y);
    }
}