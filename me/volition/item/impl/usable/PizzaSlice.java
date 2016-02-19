package me.volition.item.impl.usable;

import me.volition.mapObject.entity.Player;
import me.volition.util.ImageManager;

/**
 * Created by Demerzel on 2/4/16.
 */
public class PizzaSlice extends Usable {
    public PizzaSlice() {
        super("Single Slice", "A delicious single slice of pizza.",  "Heals 25 Tolerance.", 15, 25, ImageManager.getInstance().loadImage("/me/volition/assets/image/items/slicepizza.png"));
    }

    @Override
    public void use(Player player) {
        player.modTolerance(getValue());
    }
}
