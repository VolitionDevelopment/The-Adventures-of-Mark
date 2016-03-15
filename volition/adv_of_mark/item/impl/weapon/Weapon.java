package volition.adv_of_mark.item.impl.weapon;

import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.item.Item;
import volition.adv_of_mark.item.ItemSlot;

import java.awt.image.BufferedImage;

/**
 * Created by Demerzel on 2/3/16.
 */
public abstract class Weapon extends Item{

    //value is how much damage the weapon does
    public Weapon(String name, String desc, int value, int price, BufferedImage image) {
        super(name, desc, value + " Damage.", value, price, ItemSlot.WEAPON, image);
    }

    public void use(Player player){
        player.equip(this);
    }
}
