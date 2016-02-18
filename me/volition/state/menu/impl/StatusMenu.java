package me.volition.state.menu.impl;

import me.volition.Window;
import me.volition.entity.Player;
import me.volition.item.ItemSlot;
import me.volition.state.StateManager;
import me.volition.state.menu.BottomTextMenu;
import me.volition.util.FontManager;
import me.volition.util.GameManager;
import me.volition.util.RenderUtils;

import java.awt.*;
import java.awt.event.KeyEvent;

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
            StateManager.setCurrentState(GameManager.getInstance().getGameState());
    }

    public void render(Graphics2D g){
        super.render(g);

        RenderUtils.drawOutlinedBox(g, 30, 30, Window.WINDOW_WIDTH - 60, Window.WINDOW_HEIGHT - 120);

        g.setFont(FontManager.getSans(20));

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
        g.drawString("HELMET - ", x, y);
        if (player.getEquippedItems().containsKey(ItemSlot.HELMET))
            g.drawString(player.getEquippedItems().get(ItemSlot.HELMET).getName() + " - " + player.getEquippedItems().get(ItemSlot.HELMET).getEffect(),
                    x + g.getFontMetrics().stringWidth("HELMET - "), y);
        else
            g.drawString("NOTHING EQUIPPED.",
                    x + g.getFontMetrics().stringWidth("HELMET - "), y);

        y += 30;
        g.drawString("ARMOR - ", x, y);
        if (player.getEquippedItems().containsKey(ItemSlot.ARMOR))
            g.drawString(player.getEquippedItems().get(ItemSlot.ARMOR).getName() + " - " + player.getEquippedItems().get(ItemSlot.ARMOR).getEffect(),
                    x + g.getFontMetrics().stringWidth("ARMOR - "), y);
        else
            g.drawString("NOTHING EQUIPPED.",
                    x + g.getFontMetrics().stringWidth("ARMOR - "), y);

        y += 30;
        g.drawString("WEAPON - ", x, y);
        if (player.getEquippedItems().containsKey(ItemSlot.WEAPON))
            g.drawString(player.getEquippedItems().get(ItemSlot.WEAPON).getName() + " - " + player.getEquippedItems().get(ItemSlot.WEAPON).getEffect(),
                    x + g.getFontMetrics().stringWidth("WEAPON - "), y);
        else
            g.drawString("NOTHING EQUIPPED.",
                    x + g.getFontMetrics().stringWidth("WEAPON - "), y);
    }

    @Override
    public void keyPressed(int k){
        super.keyPressed(k);
        if (k == KeyEvent.VK_ESCAPE)
            select(0);
    }
}
