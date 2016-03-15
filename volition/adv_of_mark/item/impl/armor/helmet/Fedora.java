package volition.adv_of_mark.item.impl.armor.helmet;

import volition.adv_of_mark.util.ImageManager;

/**
 * Created by Demerzel on 2/4/16.
 */
public class Fedora extends Helmet {
    public Fedora() {
        super("Fedora", "The hat that REAL gentlemen wear.", 1, 120, ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/items/armor/helmets/fedora.png"));
    }
}
