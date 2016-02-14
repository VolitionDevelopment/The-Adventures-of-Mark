package me.volition.util;

import me.volition.entity.Player;
import me.volition.item.Item;
import me.volition.item.impl.armor.DeliveryUniform;
import me.volition.item.impl.armor.Fedora;
import me.volition.item.impl.usable.MtnDank;
import me.volition.item.impl.usable.PizzaSlice;
import me.volition.item.impl.usable.WholePizza;
import me.volition.location.placeableObject.ItemEvent;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mccloskeybr on 2/13/16.
 */
public class ItemManager {

    private static ArrayList<Class<? extends Item>> allItems;

    private static void makeAL(){
        allItems = new ArrayList<>();

        allItems.add(MtnDank.class);
        allItems.add(WholePizza.class);
        allItems.add(PizzaSlice.class);

    }

    public static Item getRandomItem() {
        if (allItems == null)
            makeAL();

        int r = new Random().nextInt(allItems.size() * 3);
        if (r < allItems.size()) {
            try {
                return allItems.get(r).newInstance();
            } catch (Exception e) { e.printStackTrace(); }
        }
        return null;
    }

    public static void onItemEvent(Player player, ItemEvent event){
        switch (event){
            case RANDOMITEM:
                player.addItem(getRandomItem());
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
