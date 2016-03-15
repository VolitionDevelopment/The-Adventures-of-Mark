package volition.adv_of_mark.mapObject.placeableObject.impl.building;

import volition.adv_of_mark.location.tile.Tile;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.mapObject.placeableObject.PlaceableObject;
import volition.adv_of_mark.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/15/16.
 */
public class Shed_tall extends PlaceableObject {

    private static BufferedImage image;

    public Shed_tall(double x, double y) {
        super(loadImage(), ObjectEvent.NONE, "Shed", "A some-what large(r) wooden person container.", true, x, y, Tile.TILE_SIZE, Tile.TILE_SIZE, Tile.TILE_SIZE);
    }

    public static BufferedImage loadImage(){
        if (image == null)
            image = ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/objects/buildings/shed_2.png");

        return image;
    }

}
