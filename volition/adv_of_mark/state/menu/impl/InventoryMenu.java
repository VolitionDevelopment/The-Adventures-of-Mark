package volition.adv_of_mark.state.menu.impl;

import volition.adv_of_mark.Window;
import volition.adv_of_mark.mapObject.entity.Player;
import volition.adv_of_mark.item.ItemSlot;
import volition.adv_of_mark.state.StateManager;
import volition.adv_of_mark.state.menu.LeftTextMenu;
import volition.adv_of_mark.util.FontManager;
import volition.adv_of_mark.util.GameManager;
import volition.adv_of_mark.util.RenderUtils;
import org.apache.commons.lang3.ArrayUtils;

import java.awt.*;
import java.awt.event.KeyEvent;


/**
 * Created by mccloskeybr on 2/12/16.
 */
public class InventoryMenu extends LeftTextMenu {
    private Player player;

    public InventoryMenu(Player player) {
        super(null, ArrayUtils.addAll(new String[]{"Go back"}, player.getInventory_strarr()), Color.WHITE, Color.RED);
        this.player = player;
    }

    @Override
    public void select(int index) {
        if (index == 0)
            StateManager.setCurrentState(GameManager.getInstance().getGameState());
        else {
            player.useItem(index - 1);
            StateManager.setCurrentState(new InventoryMenu(player));
        }

    }

    @Override
    public void render(Graphics2D g){
        super.render(g);

        g.setFont(FontManager.getSans(24));

        RenderUtils.drawOutlinedBox(g, 200, 30, Window.WINDOW_WIDTH - 250, Window.WINDOW_HEIGHT - 60);
        if (getCurrentIndex() != 0) {
            if (player.getInventory().get(getCurrentIndex() - 1).getImage() != null)
                g.drawImage(player.getInventory().get(getCurrentIndex() - 1).getImage(), 220, 100, null);

            int y = Window.WINDOW_HEIGHT / 2;

            if (player.getInventory().get(getCurrentIndex() - 1).getSlot() == ItemSlot.ARMOR)
                y += 100;

            g.setColor(Color.RED);
            g.drawString(player.getInventory().get(getCurrentIndex() - 1).getName(), 220, y);

            g.setColor(Color.WHITE);
            g.drawString(" - " + player.getInventory().get(getCurrentIndex() - 1).getEffect(),
                    220 + g.getFontMetrics().stringWidth(player.getInventory().get(getCurrentIndex() - 1).getName()), y);

            RenderUtils.drawWrappedText(g, player.getInventory().get(getCurrentIndex() - 1).getDesc(), 220, y + 30, Window.WINDOW_WIDTH - 300);        }
    }

    @Override
    public void keyPressed(int k){
        super.keyPressed(k);
        if (k == KeyEvent.VK_ESCAPE)
            select(0);
    }
}
