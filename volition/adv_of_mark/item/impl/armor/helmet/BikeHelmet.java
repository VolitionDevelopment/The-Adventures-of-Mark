package volition.adv_of_mark.item.impl.armor.helmet;

import volition.adv_of_mark.util.ImageManager;

/**
 * Created by mccloskeybr on 2/21/2016.
 */
public class BikeHelmet extends Helmet {
    public BikeHelmet() {
        super("Bicycle Helmet", "For the athletic type.", 3, 50, ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/items/armor/helmets/bikehelmet.png"));
    }
}
