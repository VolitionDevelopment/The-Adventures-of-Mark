package me.volition.item.impl.usable;

import me.volition.entity.Entity;
import me.volition.item.ItemSlot;

/**
 * Created by Demerzel on 2/3/16.
 */
public class MtnDew extends Usable {
    public MtnDew() {
        super("Mtn Dew", "A strange concoction of bleach, cat pee, and sugar. Restores 20 Brainpower", 30, ItemSlot.NONE, 20, null);
    }

    @Override
    public void use(Entity entity) {
        entity.modBrainpower(Math.min(entity.getBaseBrainpower() - entity.getBrainpower(), getMagnitude()));
        //Output to player
    }
}
