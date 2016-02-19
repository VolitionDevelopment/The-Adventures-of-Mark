package me.volition.item.impl.usable;

import me.volition.mapObject.entity.Player;
import me.volition.item.Item;
import me.volition.item.ItemSlot;

import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Usable extends Item {

    public Usable(String name, String desc, String effect, int price, int value, BufferedImage image) {
        super(name, desc, effect, value, price, ItemSlot.NONE, image);
    }

    public abstract void use(Player player);

}
