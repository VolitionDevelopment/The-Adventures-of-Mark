package me.volition.item.impl.armor;

import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/11/16.
 */
public class Jammies extends Armor {
    public Jammies() {
        super("Pajamas", "My Snazzy Tammy Bo-Bammy Jammies.", "1 Defense.", 1, 400, ImageManager.getInstance().loadImage("/me/volition/assets/image/items/jammies.png"));
    }
}
