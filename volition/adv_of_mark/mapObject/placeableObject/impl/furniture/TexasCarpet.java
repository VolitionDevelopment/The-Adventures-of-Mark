package volition.adv_of_mark.mapObject.placeableObject.impl.furniture;

import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.mapObject.placeableObject.PlaceableObject;
import volition.adv_of_mark.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/6/16.
 */
public class TexasCarpet extends PlaceableObject {

    private static BufferedImage image;

    public TexasCarpet(int x, int y) {
        super(loadImage(), ObjectEvent.NONE, "Carpet of Texas", "Yee-haw!", false, x, y, 2 * Tile.TILE_SIZE, 2 * Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/objects/furniture/texascarpet.png");

        return image;
    }
}
