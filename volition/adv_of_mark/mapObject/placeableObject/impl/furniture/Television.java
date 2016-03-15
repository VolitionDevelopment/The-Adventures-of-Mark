package volition.adv_of_mark.mapObject.placeableObject.impl.furniture;

import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.mapObject.placeableObject.PlaceableObject;
import volition.adv_of_mark.util.ImageManager;

import java.awt.image.BufferedImage;


/**
 * Created by mccloskeybr on 2/10/16.
 */
public class Television extends PlaceableObject {

    private static BufferedImage image;

    public Television(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "A good ol' Television", "Your least favorite show is on.", true, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, 2 * Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/objects/furniture/tv.png");

        return image;
    }

}
