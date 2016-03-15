package volition.adv_of_mark.item.impl.weapon;

import volition.adv_of_mark.util.ImageManager;

/**
 * Created by Demerzel on 2/3/16.
 */
public class Spoon extends Weapon {
    public Spoon() {
        super("Spoon", "A spoon from your kitchen. Don't ask me how it defeats enemies.", 3, 10, ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/items/weapons/spoon.png"));
    }
}
