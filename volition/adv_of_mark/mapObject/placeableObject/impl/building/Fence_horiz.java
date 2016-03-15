package volition.adv_of_mark.mapObject.placeableObject.impl.building;

import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.mapObject.placeableObject.PlaceableObject;
import volition.adv_of_mark.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/16/16.
 */
public class Fence_horiz extends PlaceableObject {

    private static BufferedImage image;

    public Fence_horiz(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "Fence", "It's a fence.", true, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/objects/furniture/fence_horiz.png");

        return image;
    }

}
