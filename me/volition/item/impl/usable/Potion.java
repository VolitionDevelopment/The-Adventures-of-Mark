package me.volition.item.impl.usable;

import me.volition.entity.Entity;
import me.volition.item.ItemSlot;

/**
 * Created by Demerzel on 2/3/16.
 */
public class Potion extends Usable {
    public Potion() {
        super("Potion", "Restores 20 HP.", 100, ItemSlot.DEFAULT, 20, null);
    }

    @Override
    public void use(Entity entity) {
        entity.modHealth(getMagnitude());
        //Output to player
    }
}
