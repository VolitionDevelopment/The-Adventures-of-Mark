package me.volition.item.impl.armor;

import me.volition.item.Item;
import me.volition.item.ItemSlot;

import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Armor extends Item{
    private int armor;

    public Armor(String name, String desc, int price, ItemSlot slot, int armor, BufferedImage image) {
        super(name, desc, price, slot, image);
        this.armor = armor;
    }

    public int getArmor() {
        return armor;
    }

    public void setArmor(int armor) {
        this.armor = armor;
    }
}
