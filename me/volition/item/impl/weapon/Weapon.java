package me.volition.item.impl.weapon;

import me.volition.entity.Player;
import me.volition.item.Item;
import me.volition.item.ItemSlot;

import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Weapon extends Item{
    private int damage;

    public Weapon(String name, String desc, int price, ItemSlot slot, int damage, BufferedImage image) {
        super(name, desc, price, slot, image);
        this.damage = damage;
    }

    public int getDamage() {
        return damage;
    }

    public void setDamage(int damage) {
        this.damage = damage;
    }

    public void use(Player player){
        player.equip(this);
    }
}
