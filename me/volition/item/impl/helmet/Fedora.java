package me.volition.item.impl.helmet;

import me.volition.util.ImageManager;

/**
 * Created by Demerzel on 2/4/16.
 */
public class Fedora extends Helmet {
    public Fedora() {
        super("Fedora", "The hat that REAL gentlemen wear.", "1 Defense.", 1, 120, ImageManager.getInstance().loadImage("/me/volition/assets/image/items/fedora.png"));
    }
}
