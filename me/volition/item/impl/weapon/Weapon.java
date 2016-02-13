package me.volition.item.impl.weapon;

import me.volition.entity.Player;
import me.volition.item.Item;
import me.volition.item.ItemSlot;

import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Weapon extends Item{

    //value is how much damage the weapon does
    public Weapon(String name, String desc, String effect, int value, int price, ItemSlot slot, BufferedImage image) {
        super(name, desc, effect, value, price, slot, image);
    }

    public void use(Player player){
        player.equip(this);
    }
}
