package volition.adv_of_mark.item.impl.usable;

import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.util.ImageManager;

/**
 * Created by Demerzel on 2/4/16.
 */
public class PizzaSlice extends Usable {
    public PizzaSlice() {
        super("Single Slice", "A delicious single slice of pizza.",  "Heals 25 Tolerance.", 15, 25, ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/items/usables/slicepizza.png"));
    }

    @Override
    public void use(Player player) {
        player.modTolerance(getValue());
    }
}
