package me.volition.item.impl.weapon;

import me.volition.util.ImageManager;

/**
 * Created by mccloskeybr on 2/21/2016.
 */
public class Book extends Weapon {
    public Book() {
        super("Book", "Hit 'em with a dose of learnage!", 5, 40, ImageManager.getInstance().loadImage("/me/volition/assets/image/items/weapons/book.png"));
    }
}
