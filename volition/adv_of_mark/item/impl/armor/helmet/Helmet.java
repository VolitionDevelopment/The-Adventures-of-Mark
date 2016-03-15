package volition.adv_of_mark.item.impl.armor.helmet;

import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.item.Item;
import volition.adv_of_mark.item.ItemSlot;

import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/14/2016.
 */
public class Helmet extends Item {
    public Helmet(String name, String desc, int value, int price, BufferedImage image) {
        super(name, desc, value + " Defense.", value, price, ItemSlot.HELMET, image);
    }

    @Override
    public void use(Player player) {
        player.equip(this);
    }
}
