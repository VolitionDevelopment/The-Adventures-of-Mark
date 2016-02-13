package me.volition.item.impl.usable;

import me.volition.entity.Player;
import me.volition.item.ItemSlot;
import me.volition.util.ImageManager;

/**
 * Created by Demerzel on 2/3/16.
 */
public class MtnDank extends Usable {
    public MtnDank() {
        super("Mountain Dank", "A strange concoction of bleach, cat pee, and sugar.", "Restores 20 Brainpower.", 30, ItemSlot.NONE, 20, new ImageManager().loadImage("/me/volition/assets/image/items/mtndank.png"));
    }

    @Override
    public void use(Player player) {
        player.modBrainpower(Math.min(player.getBaseBrainpower() - player.getBrainpower(), getMagnitude()));
    }
}
