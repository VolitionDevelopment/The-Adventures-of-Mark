package volition.adv_of_mark.util;

import volition.adv_of_mark.item.Item;
import volition.adv_of_mark.item.impl.armor.*;
import volition.adv_of_mark.item.impl.weapon.*;
import volition.adv_of_mark.item.impl.armor.helmet.*;
import volition.adv_of_mark.item.impl.usable.*;
import volition.adv_of_mark.mapObject.MapObject;
import volition.adv_of_mark.mapObject.ObjectEvent;
import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.mapObject.placeableObject.PlaceableObject;
import volition.adv_of_mark.mapObject.placeableObject.impl.furniture.*;
import volition.adv_of_mark.state.menu.ingamemenu.game.DialogueMenu;
import volition.adv_of_mark.state.menu.ingamemenu.game.ShopMenu;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * Created by mccloskeybr on 2/13/16.
 */
public class ObjectManager {

    private static ArrayList<Class<? extends Item>> allUsables;
    private static ArrayList<Class<? extends Item>> allWeapons;
    private static ArrayList<Class<? extends Item>> allArmor;

    private static void registerUsables(){

        allUsables = new ArrayList<>();

        allUsables.add(MtnDank.class);
        allUsables.add(WholePizza.class);
        allUsables.add(PizzaSlice.class);

    }

    private static void registerArmor(){

        allArmor = new ArrayList<>(); //includes helmets

        allArmor.add(Jammies.class);
        allArmor.add(DeliveryUniform.class);
        allArmor.add(BusinessOutfit.class);
        allArmor.add(GraphicTee.class);
        allArmor.add(LeopardSpeedo.class);
        allArmor.add(Fedora.class);
        allArmor.add(BikeHelmet.class);
        allArmor.add(BallCap.class);

    }

    private static void registerWeapons(){

        allWeapons = new ArrayList<>();

        allWeapons.add(Spoon.class);
        allWeapons.add(Spork.class);
        allWeapons.add(LaxStick.class);
        allWeapons.add(Book.class);

    }

    public static Item getRandomUsable(int rng_multiplier) {
        if (allUsables == null)
            registerUsables();

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
            registerArmor();

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
            registerWeapons();

        int r = new Random().nextInt(allWeapons.size() * rng_multiplier);
        if (r < allWeapons.size()) {
            try {
                return allWeapons.get(r).newInstance();
            } catch (Exception e) { e.printStackTrace(); }
        }
        return null;
    }

    public static void onObjectEvent(MapObject mapObject){

        Player player = GameManager.getInstance().getGameState().getPlayer();

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
