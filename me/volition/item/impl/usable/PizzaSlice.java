package me.volition.item.impl.usable;

import me.volition.entity.Entity;
import me.volition.entity.Player;
import me.volition.item.ItemSlot;

import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/4/16.
 */
public class PizzaSlice extends Usable {
    public PizzaSlice() {
        super("Slice of Pizza", "A delicious single slice of pizza. Heals 25 Tolerance.", 15, ItemSlot.NONE, 25, null);
    }

    @Override
    public void use(Player player) {
        player.modTolerance(Math.min(player.getBaseTolerance() - player.getTolerance(), getMagnitude()));
    }
}
