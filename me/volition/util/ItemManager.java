package me.volition.util;

import me.volition.entity.Player;
import me.volition.item.Item;
import me.volition.item.impl.armor.DeliveryUniform;
import me.volition.item.impl.helmet.Fedora;
import me.volition.item.impl.usable.MtnDank;
import me.volition.item.impl.usable.PizzaSlice;
import me.volition.item.impl.usable.WholePizza;
import me.volition.location.placeableObject.ObjectEvent;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mccloskeybr on 2/13/16.
 */
public class ItemManager {

    private static ArrayList<Class<? extends Item>> allItems;

    private static void registerAllItems(){
        allItems = new ArrayList<>();

        allItems.add(MtnDank.class);
        allItems.add(WholePizza.class);
        allItems.add(PizzaSlice.class);

    }

    public static Item getRandomItem(int rng_multiplier) {
        if (allItems == null)
            registerAllItems();

        int r = new Random().nextInt(allItems.size() * rng_multiplier);
        if (r < allItems.size()) {
            try {
                return allItems.get(r).newInstance();
            } catch (Exception e) { e.printStackTrace(); }
        }
        return null;
    }

    public static void onObjectEvent(Player player, ObjectEvent event){
        switch (event){
            case RANDOMITEM:
                player.addItem(getRandomItem(1));
                break;
            case PICKUP_FEDORA:
                player.addItem(new Fedora());
                break;
            case PICKUP_UNIFORM:
                player.addItem(new DeliveryUniform());
                break;
            case NONE:
                break;
        }
    }
}
