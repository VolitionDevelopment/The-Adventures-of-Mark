package me.volition.item.impl.usable;

import me.volition.entity.Entity;
import me.volition.entity.Player;
import me.volition.item.Item;
import me.volition.item.ItemSlot;

import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Usable extends Item {
    private int magnitude;

    public Usable(String name, String desc, int price, ItemSlot slot, int magnitude, BufferedImage image) {
        super(name, desc, price, slot, image);
        this.magnitude = magnitude;
    }

    public abstract void use(Player player);

    public int getMagnitude() {
        return magnitude;
    }

    public void setMagnitude(int magnitude) {
        this.magnitude = magnitude;
    }
}
