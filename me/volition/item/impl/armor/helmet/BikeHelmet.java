package me.volition.item.impl.armor.helmet;

import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/21/2016.
 */
public class BikeHelmet extends Helmet {
    public BikeHelmet() {
        super("Bicycle Helmet", "For the athletic type.", 3, 50, ImageManager.getInstance().loadImage("/me/volition/assets/image/items/armor/helmets/bikehelmet.png"));
    }
}
