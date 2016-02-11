package me.volition.item;

import me.volition.entity.Player;

import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Item {
    private String name;
    private String desc;
    private int price;
    private ItemSlot slot;
    private BufferedImage image;

    public Item(String name, String desc, int price, ItemSlot slot, BufferedImage image) {
        this.name = name;
        this.desc = desc;
        this.price = price;
        this.slot = slot;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public ItemSlot getSlot() {
        return slot;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setSlot(ItemSlot slot) {
        this.slot = slot;
    }

    public abstract void use(Player player);
}