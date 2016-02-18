package me.volition.state.menu.ingamemenu.battle;

import me.volition.Window;
import org.apache.commons.lang3.*;

import java.awt.*;
import java.awt.event.KeyEvent;


/**
 * Created by mccloskeybr on 2/8/2016.
 */
public class ItemMenu extends BattleMenu {
    public ItemMenu(BattleMenu prevMenu) {
        super(ArrayUtils.addAll(new String[]{"Go back"}, prevMenu.getBattleState().getPlayer().getInventory_strarr()), prevMenu);
    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            getPrevMenu().setSubMenu(null);
        else {
            getBattleState().getPlayer().useItem(currentIndex - 1);
            getBattleState().switchTurns();
        }
    }

    @Override
    public void render(Graphics g){
        g.setFont(new Font("Determination Sans", Font.PLAIN, 14));

        int x = getX();
        int y = 3 * Window.WINDOW_HEIGHT / 4;

        for (int i = 0; i < getOptions().length; i++) {
            if (i == getCurrentIndex())
                g.setColor(Color.RED);
            else
                g.setColor(Color.WHITE);

            g.drawString(getOptions()[i], x, y);

            y += 30;
            if (y >= Window.WINDOW_HEIGHT - 30) {
                y = 3 * Window.WINDOW_HEIGHT / 4;
                x += 120;
            }
        }
    }
}
