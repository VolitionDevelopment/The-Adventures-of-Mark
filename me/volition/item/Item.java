package me.volition.item;

import me.volition.*;
import me.volition.Window;
import me.volition.entity.Player;
import me.volition.util.RenderUtils;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Item {
    private String name;
    private String desc;
    private int price;
    private int value;
    private ItemSlot slot;
    private BufferedImage image;

    public Item(String name, String desc, int value, int price, ItemSlot slot, BufferedImage image) {
        this.name = name;
        this.desc = desc;
        this.value = value;
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

    public int getValue(){
        return value;
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

    public void renderDescription(Graphics g){
        if (image != null)
            g.drawImage(image, 220, 100, null);

        g.setColor(Color.RED);
        g.drawString(name, 220, Window.WINDOW_HEIGHT / 2);
        
        g.setColor(Color.WHITE);
        RenderUtils.drawWrappedText(g, desc, 220, Window.WINDOW_HEIGHT / 2 + 30, Window.WINDOW_WIDTH - 300);
    }
}