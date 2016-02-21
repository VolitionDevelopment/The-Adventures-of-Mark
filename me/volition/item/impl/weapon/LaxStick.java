package me.volition.item.impl.weapon;

import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/21/2016.
 */
public class LaxStick extends Weapon {
    public LaxStick() {
        super("Lax Stick", "What even is this thing?", 5, 50, ImageManager.getInstance().loadImage("/me/volition/assets/image/items/weapons/laxstick.png"));
    }
}
