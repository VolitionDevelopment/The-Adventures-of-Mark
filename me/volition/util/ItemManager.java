package me.volition.util;

import me.volition.mapObject.MapObject;
import me.volition.mapObject.entity.Player;
import me.volition.item.Item;
import me.volition.item.impl.armor.DeliveryUniform;
import me.volition.item.impl.helmet.Fedora;
import me.volition.item.impl.usable.MtnDank;
import me.volition.item.impl.usable.PizzaSlice;
import me.volition.item.impl.usable.WholePizza;
import me.volition.mapObject.placeableObject.ObjectEvent;
import me.volition.state.menu.ingamemenu.game.DialogueMenu;
import me.volition.state.menu.ingamemenu.game.RestaurantShopMenu;

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

    public static void onObjectEvent(MapObject mapObject){

        Player player = GameManager.getInstance().getPlayer();

        ObjectEvent event = mapObject.getOnInspect();

        switch (event){
            case RANDOMITEM:
                Item item = getRandomItem(1);
                if (item != null) {
                    player.addItem(getRandomItem(1));
                    GameManager.getInstance().getGameState().setInGameMenu(new DialogueMenu(player, "I found a " + item.getName() + "."));
                }
                break;
            case OPEN_ITEMSTORE:
                GameManager.getInstance().getGameState().setInGameMenu(new RestaurantShopMenu());
                break;
            case PICKUP_FEDORA:
                player.addItem(new Fedora());
                break;
            case PICKUP_UNIFORM:
                player.addItem(new DeliveryUniform());
                break;
            case NONE:
                GameManager.getInstance().getGameState().setInGameMenu(new DialogueMenu(player, mapObject.getName() + " - " + mapObject.getDesc()));
                break;
        }
    }
}
