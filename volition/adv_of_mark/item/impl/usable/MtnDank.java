package volition.adv_of_mark.item.impl.usable;

import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.util.ImageManager;

/**
 * Created by Demerzel on 2/3/16.
 */
public class MtnDank extends Usable {
    public MtnDank() {
        super("Mtn Dank", "A strange concoction of bleach, cat pee, and sugar.", "Restores 20 Brainpower.", 30, 20, ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/items/usables/mtndank.png"));
    }

    @Override
    public void use(Player player) {
        player.modBrainpower(getValue());
    }
}
