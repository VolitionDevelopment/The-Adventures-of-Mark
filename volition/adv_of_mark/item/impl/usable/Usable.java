package volition.adv_of_mark.item.impl.usable;

import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.item.Item;
import volition.adv_of_mark.item.ItemSlot;

import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Usable extends Item {

    public Usable(String name, String desc, String effect, int price, int value, BufferedImage image) {
        super(name, desc, effect, value, price, ItemSlot.NONE, image);
    }

    public abstract void use(Player player);

}
