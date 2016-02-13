package me.volition.item.impl.armor;

import me.volition.item.ItemSlot;
import me.volition.util.ImageManager;

import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/4/16.
 */
public class Fedora extends Armor {
    public Fedora() {
        super("Fedora", "The hat that a REAL gentleman wears.", "1 Defense.", 1, 120, ItemSlot.HEAD, new ImageManager().loadImage("/me/volition/assets/image/items/fedora.png"));
    }
}
