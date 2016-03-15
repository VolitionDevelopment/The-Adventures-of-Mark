package volition.adv_of_mark.item.impl.weapon;

import volition.adv_of_mark.util.ImageManager;

/**
 * Created by mccloskeybr on 2/21/2016.
 */
public class Spork extends Weapon {
    public Spork() {
        super("Spork", "Its like a spoon, but slightly sharper.", 4, 20, ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/items/weapons/spork.png"));
    }
}
