package me.volition.item.impl.armor;

import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/21/2016.
 */
public class BusinessOutfit extends Armor {
    public BusinessOutfit() {
        super("Business Outfit", "Ready to tackle the world.", 6, 150, ImageManager.getInstance().loadImage("/me/volition/assets/image/items/armor/businessoutfit.png"));
    }
}
