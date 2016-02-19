package me.volition.item.impl.usable;

import me.volition.mapObject.entity.Player;
import me.volition.util.ImageManager;

/**
 * Created by Demerzel on 2/3/16.
 */
public class MtnDank extends Usable {
    public MtnDank() {
        super("Mtn Dank", "A strange concoction of bleach, cat pee, and sugar.", "Restores 20 Brainpower.", 30, 20, ImageManager.getInstance().loadImage("/me/volition/assets/image/items/mtndank.png"));
    }

    @Override
    public void use(Player player) {
        player.modBrainpower(getValue());
    }
}
