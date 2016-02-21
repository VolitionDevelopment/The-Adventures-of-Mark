package me.volition.item.impl.weapon;

import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/21/2016.
 */
public class Spork extends Weapon {
    public Spork() {
        super("Spork", "Its like a spoon, but slightly sharper.", 4, 20, ImageManager.getInstance().loadImage("/me/volition/assets/image/items/weapons/spork.png"));
    }
}
