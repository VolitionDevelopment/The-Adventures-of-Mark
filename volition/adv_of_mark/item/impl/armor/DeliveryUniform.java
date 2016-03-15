package volition.adv_of_mark.item.impl.armor;

import volition.adv_of_mark.util.ImageManager;

/**
 * Created by Demerzel on 2/4/16.
 */
public class DeliveryUniform extends Armor {
    public DeliveryUniform() {
        super("Delivery Uniform", "A uniform bestowed upon you by Peppito himself.", 4, 0, ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/items/armor/pizzauniform.png"));
    }
}
