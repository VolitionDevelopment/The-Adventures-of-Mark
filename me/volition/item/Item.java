package me.volition.item;

import me.volition.entity.Player;

import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Item {
    private String name;
    private String desc;
    private String effect;
    private int price;
    private int value;
    private ItemSlot slot;
    private BufferedImage image;

    public Item(String name, String desc, String effect, int value, int price, ItemSlot slot, BufferedImage image) {
        this.name = name;
        this.desc = desc;
        this.effect = effect;
        this.value = value;
        this.price = price;
        this.slot = slot;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getEffect(){
        return effect;
    }

    public int getValue(){
        return value;
    }

    public int getPrice() {
        return price;
    }

    public ItemSlot getSlot() {
        return slot;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public void setSlot(ItemSlot slot) {
        this.slot = slot;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public BufferedImage getImage(){
        return image;
    }



    public abstract void use(Player player);

}