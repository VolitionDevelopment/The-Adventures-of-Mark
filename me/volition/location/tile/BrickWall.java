package me.volition.location.tile;

import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class BrickWall extends Tile {
    public BrickWall(int x, int y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/rooms/tiles/brickwall.png"), true, x, y);
    }
}
