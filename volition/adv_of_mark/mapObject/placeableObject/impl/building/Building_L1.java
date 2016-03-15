package volition.adv_of_mark.mapObject.placeableObject.impl.building;

import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.mapObject.placeableObject.PlaceableObject;
import volition.adv_of_mark.util.ImageManager;

import java.awt.image.BufferedImage;


/**
 * Created by mccloskeybr on 2/15/16.
 */
public class Building_L1 extends PlaceableObject {

    private static BufferedImage image;

    public Building_L1(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "building_1", "The dev who created this building probably forgot about naming it.", true, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/objects/buildings/building_L_1.png");

        return image;
    }

}
