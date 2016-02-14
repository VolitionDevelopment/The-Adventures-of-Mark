package me.volition.location.tile;

import me.volition.util.ImageManager;


/**
 * Created by mccloskeybr on 2/5/16.
 */
public class WoodTile extends Tile {
    public WoodTile(int x, int y) {
        super(ImageManager.getInstance().loadImage("/me/volition/assets/image/tiles/woodtile.png"), false, x, y);
    }
}
