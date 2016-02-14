package me.volition.item.impl.armor;

import me.volition.entity.Player;
import me.volition.item.Item;
import me.volition.item.ItemSlot;

import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Armor extends Item{

    //value is how much damage the armor piece blocks
    public Armor(String name, String desc, String effect, int value, int price, BufferedImage image) {
        super(name, desc, effect, value, price, ItemSlot.ARMOR, image);
    }

    public void use(Player player) {
        player.equip(this);
    }
}
