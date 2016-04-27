package volition.adv_of_mark.state.menu.ingamemenu.game;

import volition.adv_of_mark.Window;
import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.item.Item;
import volition.adv_of_mark.state.menu.ingamemenu.InGameMenu;
import volition.adv_of_mark.util.FontManager;
import volition.adv_of_mark.util.GameManager;
import volition.adv_of_mark.util.ObjectManager;
import volition.adv_of_mark.util.RenderUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Created by mccloskeybr on 2/17/16.
 */
public class ShopMenu extends InGameMenu {

    private static ArrayList<Item> shopItems;
    private static String[] items;

    public ShopMenu() {
        super(ArrayUtils.addAll(new String[]{"Exit"}, generateRandomItems()));
    }

    public static String[] generateRandomItems(){

        int numItemsInShop = 3;

        shopItems = new ArrayList<>();

        while (shopItems.size() < numItemsInShop)
            shopItems.add(ObjectManager.getRandomItem(1));

        items = new String[shopItems.size()];

        for (int i = 0; i < items.length; i++)
            items[i] = shopItems.get(i).getName() + " - $" + shopItems.get(i).getPrice();

        return items;

    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            GameManager.getInstance().getGameState().setInGameMenu(null);
        else {

            Player player = GameManager.getInstance().getGameState().getPlayer();
            Item item = shopItems.get(currentIndex - 1);

            if (player.getMoney() >= item.getPrice()) {

                player.modMoney(-item.getPrice());

                player.addItem(item);
                shopItems.remove(item);

                items = new String[shopItems.size()];

                for (int i = 0; i < items.length; i++)
                    items[i] = shopItems.get(i).getName() + " - $" + shopItems.get(i).getPrice();

                setOptions(ArrayUtils.addAll(new String[]{"Exit"}, items));

            }
        }
    }

    @Override
    public void keyPressed(int k){
        super.keyPressed(k);

        if (k == KeyEvent.VK_D) {
            setCurrentIndex(getCurrentIndex() + 1);
            onKeyPress();
        }else if (k == KeyEvent.VK_A) {
            setCurrentIndex(getCurrentIndex() - 1);
            onKeyPress();
        }
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
