package me.volition.item.impl.armor;

import me.volition.entity.Player;
import me.volition.item.ItemSlot;

import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/4/16.
 */
public class DeliveryPants extends Armor {
    public DeliveryPants() {
        super("Delivery Pants", "A pair of pants bestowed upon you by Pizza Hut.", 5, 0, ItemSlot.LEG, 3, null);
    }
}
