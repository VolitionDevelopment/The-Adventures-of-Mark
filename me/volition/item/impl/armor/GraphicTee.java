package me.volition.item.impl.armor;

import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/21/2016.
 */
public class GraphicTee extends Armor {
    public GraphicTee() {
        super("Graphic Tee", "What are you, 15?", 4, 30, ImageManager.getInstance().loadImage("/me/volition/assets/image/items/armor/graphictee.png"));
    }
}
