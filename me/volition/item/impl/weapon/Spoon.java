package me.volition.item.impl.weapon;

import me.volition.util.ImageManager;

/**
 * Created by Demerzel on 2/3/16.
 */
public class Spoon extends Weapon {
    public Spoon() {
        super("Spoon", "A spoon from your kitchen. Don't ask me how it defeats enemies.", 3, 10, ImageManager.getInstance().loadImage("/me/volition/assets/image/items/weapons/spoon.png"));
    }
}
