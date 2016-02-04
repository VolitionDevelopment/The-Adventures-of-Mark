package me.volition.item.impl.usable;

import me.volition.entity.Entity;
import me.volition.item.ItemSlot;

import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/4/16.
 */
public class WholePizza extends Usable {
    public WholePizza() {
        super("Whole Pizza", "An entire pizza. The smell makes your eyes water and your stomach grumble. Restores all of your Tolerance", 60, ItemSlot.NONE, 0, null);
    }

    @Override
    public void use(Entity entity) {
        entity.setTolerance(entity.getBaseTolerance());
    }
}
