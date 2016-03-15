package volition.adv_of_mark.item.impl.armor.helmet;

import volition.adv_of_mark.util.ImageManager;

/**
 * Created by mccloskeybr on 2/21/2016.
 */
public class BallCap extends Helmet {
    public BallCap() {
        super("Baseball Cap", "A true sports guy.", 2, 20, ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/items/armor/helmets/ballcap.png"));
    }
}
