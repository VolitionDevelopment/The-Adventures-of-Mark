package me.volition.util;

import me.volition.item.impl.armor.Jammies;
import me.volition.item.impl.weapon.Spoon;
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
import me.volition.state.menu.ingamemenu.game.ShopMenu;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by mccloskeybr on 2/13/16.
 */
public class ObjectManager {

    private static ArrayList<Class<? extends Item>> allUsables;
    private static ArrayList<Class<? extends Item>> allWeapons;
    private static ArrayList<Class<? extends Item>> allArmor;

    private static void registerAllItems(){
        allUsables = new ArrayList<>();

        allUsables.add(MtnDank.class);
        allUsables.add(WholePizza.class);
        allUsables.add(PizzaSlice.class);

        allArmor = new ArrayList<>();
        allArmor.add(Jammies.class);
        allArmor.add(DeliveryUniform.class);

        allWeapons = new ArrayList<>();
        allWeapons.add(Spoon.class);

    }

    public static Item getRandomUsable(int rng_multiplier) {
        if (allUsables == null)
            registerAllItems();

        int r = new Random().nextInt(allUsables.size() * rng_multiplier);
        if (r < allUsables.size()) {
            try {
                return allUsables.get(r).newInstance();
            } catch (Exception e) { e.printStackTrace(); }
        }
        return null;
    }

    public static Item getRandomArmor(int rng_multiplier) {
        if (allArmor == null)
            registerAllItems();

        int r = new Random().nextInt(allArmor.size() * rng_multiplier);
        if (r < allArmor.size()) {
            try {
                return allArmor.get(r).newInstance();
            } catch (Exception e) { e.printStackTrace(); }
        }
        return null;
    }

    public static Item getRandomWeapon(int rng_multiplier) {
        if (allWeapons == null)
            registerAllItems();

        int r = new Random().nextInt(allWeapons.size() * rng_multiplier);
        if (r < allWeapons.size()) {
            try {
                return allWeapons.get(r).newInstance();
            } catch (Exception e) { e.printStackTrace(); }
        }
        return null;
    }

    public static void onObjectEvent(MapObject mapObject){

        Player player = GameManager.getInstance().getPlayer();

        ObjectEvent event = mapObject.getOnInspect();

        switch (event){
            case RANDOMUSABLE:

                Item item = getRandomUsable(1);
                if (item != null) {
                    player.addItem(getRandomUsable(1));
                    GameManager.getInstance().getGameState().setInGameMenu(new DialogueMenu(player, "I found a " + item.getName() + "."));
                }

                break;

            case OPEN_ITEMSTORE:

                GameManager.getInstance().getGameState().setInGameMenu(new ShopMenu());

                break;

            case PICKUP_FEDORA:

                if (player.addItem(new Fedora()))
                    GameManager.getInstance().getGameState().setInGameMenu(new DialogueMenu(player, mapObject.getName() + " - " + mapObject.getDesc()));

                break;

            case PICKUP_UNIFORM:

                if (player.addItem(new DeliveryUniform()))
                    GameManager.getInstance().getGameState().setInGameMenu(new DialogueMenu(player, mapObject.getName() + " - " + mapObject.getDesc()));

                break;

            case NONE:
                GameManager.getInstance().getGameState().setInGameMenu(new DialogueMenu(player, mapObject.getName() + " - " + mapObject.getDesc()));
                break;
        }
    }
}
