package me.volition.item.impl.armor.helmet;

import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/21/2016.
 */
public class BallCap extends Helmet {
    public BallCap() {
        super("Baseball Cap", "A true sports guy.", 2, 20, ImageManager.getInstance().loadImage("/me/volition/assets/image/items/armor/helmets/ballcap.png"));
    }
}
