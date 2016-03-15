package volition.adv_of_mark.item.impl.weapon;

import volition.adv_of_mark.util.ImageManager;

/**
 * Created by mccloskeybr on 2/21/2016.
 */
public class Book extends Weapon {
    public Book() {
        super("Book", "Hit 'em with a dose of learnage!", 5, 40, ImageManager.getInstance().loadImage("/volition/adv_of_mark/assets/image/items/weapons/book.png"));
    }
}
