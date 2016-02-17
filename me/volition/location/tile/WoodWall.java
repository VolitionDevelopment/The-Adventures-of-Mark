package me.volition.location.tile;

import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/5/16.
 */
public class WoodWall extends Tile {
    public WoodWall(int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/tiles/woodwall.png"), true, x, y);
    }
}
