package me.volition.state.menu.ingamemenu.game;

import me.volition.Window;
import me.volition.mapObject.entity.Player;
import me.volition.item.Item;
import me.volition.state.menu.ingamemenu.InGameMenu;
import me.volition.util.FontManager;
import me.volition.util.GameManager;
import me.volition.util.ObjectManager;
import me.volition.util.RenderUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by mccloskeybr on 2/17/16.
 */
public class ShopMenu extends InGameMenu {

    private static ArrayList<Item> shopItems;
    private static String[] shopItems_str;

    public ShopMenu() {
        super(ArrayUtils.addAll(new String[]{"Exit"}, shopItems_str));
    }

    //TYPE is type of item, 0 is usable, 1 is armor, 2 is weapon
    public static void generateRandomItems(int type){

        int numItemsInShop = 3;

        shopItems = new ArrayList<>();

        if (type == 0)
            while (shopItems.size() < numItemsInShop)
                shopItems.add(ObjectManager.getRandomUsable(1));
        else if (type == 1)
            while (shopItems.size() < numItemsInShop)
                shopItems.add(ObjectManager.getRandomArmor(1));
        else
            while (shopItems.size() < numItemsInShop)
                shopItems.add(ObjectManager.getRandomWeapon(1));

        shopItems_str = new String[shopItems.size()];

        for (int i = 0; i < shopItems_str.length; i++)
            shopItems_str[i] = shopItems.get(i).getName() + " - $" + shopItems.get(i).getPrice();

    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            GameManager.getInstance().getGameState().setInGameMenu(null);
        else {

            Player player = GameManager.getInstance().getPlayer();
            Item item = shopItems.get(currentIndex - 1);

            if (player.getMoney() >= item.getPrice()) {

                player.modMoney(-item.getPrice());

                player.addItem(item);
                shopItems.remove(item);

                shopItems_str = new String[shopItems.size()];

                for (int i = 0; i < shopItems_str.length; i++)
                    shopItems_str[i] = shopItems.get(i).getName() + " - $" + shopItems.get(i).getPrice();

                setOptions(ArrayUtils.addAll(new String[]{"Go Back"}, shopItems_str));

                GameManager.getInstance().getGameState().setInGameMenu(new ShopMenu());

            }
        }
    }

    @Override
    public void keyPressed(int k){
        super.keyPressed(k);

        if (k == KeyEvent.VK_D)
            setCurrentIndex(getCurrentIndex() + 1);
        else if (k == KeyEvent.VK_A)
            setCurrentIndex(getCurrentIndex() - 1);
    }

    @Override
    public void render(Graphics2D g) {

        g.setFont(FontManager.getSans(20));

        RenderUtils.drawOutlinedBox(g, 30, 50, Window.WINDOW_WIDTH - 60, Window.WINDOW_HEIGHT - 350);
        RenderUtils.drawOutlinedBox(g, 30, Window.WINDOW_HEIGHT - 240, Window.WINDOW_WIDTH - 60, 75);

        int x = 50;
        for (int i = 0; i < getOptions().length; i++){
            if (getCurrentIndex() == i)
                g.setColor(Color.RED);
            else
                g.setColor(Color.WHITE);

            g.drawString(getOptions()[i], x, Window.WINDOW_HEIGHT - 200);

            x += g.getFontMetrics().stringWidth(getOptions()[i] + 50);

            if (i < shopItems.size())
                g.drawImage(shopItems.get(i).getImage(), 100 + 256 * i, 100, null);

        }

    }
}
