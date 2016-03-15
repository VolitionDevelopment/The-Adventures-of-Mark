package volition.adv_of_mark.item.impl.weapon;

import volition.adv_of_mark.util.ImageManager;

/**
 * Created by mccloskeybr on 2/21/2016.
 */
public class LaxStick extends Weapon {
    public LaxStick() {
        super("Lax Stick", "What even is this thing?", 5, 50, ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/items/weapons/laxstick.png"));
    }
}
