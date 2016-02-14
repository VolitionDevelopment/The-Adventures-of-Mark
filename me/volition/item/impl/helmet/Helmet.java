package me.volition.item.impl.helmet;

import me.volition.entity.Player;
import me.volition.item.Item;
import me.volition.item.ItemSlot;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/14/2016.
 */
public class Helmet extends Item {
    public Helmet(String name, String desc, String effect, int value, int price, BufferedImage image) {
        super(name, desc, effect, value, price, ItemSlot.HELMET, image);
    }

    @Override
    public void use(Player player) {
        player.equip(this);
    }
}
