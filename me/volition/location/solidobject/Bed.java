package me.volition.location.solidobject;

import me.volition.location.tile.Tile;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/5/16.
 */
public class Bed extends SolidObject {
    public Bed(Tile[][] tilemap, int x, int y) {
        super(new ImageManager().loadImage("/me/volition/assets/image/objects/bed.png"), tilemap, x, y, 2, 1);
    }
}
