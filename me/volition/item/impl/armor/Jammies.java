package me.volition.item.impl.armor;

import me.volition.item.ItemSlot;
import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/11/16.
 */
public class Jammies extends Armor {
    public Jammies() {
        super("Pajamas", "My Snazzy Tammy Bo-Bammy Jammies.", "1 Defense.", 1, 400, ItemSlot.ARMOR, new ImageManager().loadImage("/me/volition/assets/image/items/jammies.png"));
    }
}
