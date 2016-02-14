package me.volition.item.impl.armor;

import me.volition.item.ItemSlot;
import me.volition.util.ImageManager;

/**
 * Created by Demerzel on 2/4/16.
 */
public class DeliveryUniform extends Armor {
    public DeliveryUniform() {
        super("Delivery Uniform", "A uniform bestowed upon you by Peppito himself.", "4 Defense.", 4, 0, ItemSlot.ARMOR, new ImageManager().loadImage("/me/volition/assets/image/items/pizzauniform.png"));
    }
}
