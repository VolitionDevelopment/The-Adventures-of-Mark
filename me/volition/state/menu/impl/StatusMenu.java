package me.volition.state.menu.impl;

import me.volition.Window;
import me.volition.entity.Player;
import me.volition.item.ItemSlot;
import me.volition.state.StateManager;
import me.volition.state.menu.BottomTextMenu;
import me.volition.util.GameManager;
import me.volition.util.RenderUtils;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/13/16.
 */
public class StatusMenu extends BottomTextMenu {

    private Player player;

    public StatusMenu(Player player) {
        super(null, new String[]{"Go back"}, Color.WHITE, Color.RED);
        this.player = player;
    }

    @Override
    public void select(int index) {
        if (index == 0)
            StateManager.setCurrentState(GameManager.getGameState());
    }

    public void render(Graphics g){
        super.render(g);

        RenderUtils.drawOutlinedBox(g, 30, 30, Window.WINDOW_WIDTH - 60, Window.WINDOW_HEIGHT - 120);

        g.setFont(new Font("Determination Sans", Font.PLAIN, 20));

        int x = 100;
        int y = 100;

        g.setColor(Color.RED);

        g.drawString(player.getName(), x, y);
        g.setColor(Color.WHITE);
        y += 30;
        g.drawString("TOL: " + player.getTolerance() + " / " + player.getBaseTolerance(), x, y);
        y += 30;
        g.drawString("BP: " + player.getBrainpower() + " / " + player.getBaseBrainpower(), x, y);
        y += 30;
        g.drawString("LV: " + player.getLevel(), x, y);
        y += 30;
        g.drawString("EXP: " + player.getExp() + " / " + player.nextLevel(), x, y);

        y += 60;
        g.setColor(Color.RED);
        g.drawString("EQUIP", x, y);
        g.setColor(Color.WHITE);

        y += 60;
        g.drawString("HEAD - ", x, y);
        if (player.getEquippedItems().containsKey(ItemSlot.HEAD))
            g.drawString(player.getEquippedItems().get(ItemSlot.HEAD).getName() + " - " + player.getEquippedItems().get(ItemSlot.HEAD).getEffect(),
                    x + g.getFontMetrics().stringWidth("HEAD - "), y);
        else
            g.drawString("NOTHING EQUIPPED.",
                    x + g.getFontMetrics().stringWidth("HEAD - "), y);

        y += 30;
        g.drawString("CHEST - ", x, y);
        if (player.getEquippedItems().containsKey(ItemSlot.CHEST))
            g.drawString(player.getEquippedItems().get(ItemSlot.CHEST).getName() + " - " + player.getEquippedItems().get(ItemSlot.CHEST).getEffect(),
                    x + g.getFontMetrics().stringWidth("CHEST - "), y);
        else
            g.drawString("NOTHING EQUIPPED.",
                    x + g.getFontMetrics().stringWidth("CHEST - "), y);

        y += 30;
        g.drawString("WEAPON - ", x, y);
        if (player.getEquippedItems().containsKey(ItemSlot.WEAPON))
            g.drawString(player.getEquippedItems().get(ItemSlot.WEAPON).getName() + " - " + player.getEquippedItems().get(ItemSlot.WEAPON).getEffect(),
                    x + g.getFontMetrics().stringWidth("WEAPON - "), y);
        else
            g.drawString("NOTHING EQUIPPED.",
                    x + g.getFontMetrics().stringWidth("WEAPON - "), y);    }
}
