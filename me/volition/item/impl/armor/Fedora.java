package me.volition.item.impl.armor;

import me.volition.item.ItemSlot;
import me.volition.util.ImageManager;

/**
 * Created by Demerzel on 2/4/16.
 */
public class Fedora extends Armor {
    public Fedora() {
        super("Fedora", "The hat that REAL gentlemen wear.", "1 Defense.", 1, 120, ItemSlot.HELMET, new ImageManager().loadImage("/me/volition/assets/image/items/fedora.png"));
    }
}
