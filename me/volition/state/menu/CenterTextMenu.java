package me.volition.state.menu;

import me.volition.*;

import java.awt.*;
import java.awt.Window;
import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/4/16.
 */
public abstract class CenterTextMenu extends MenuState {

    public CenterTextMenu(BufferedImage backgroundImage, String[] menuOptions, Color textColor, Color textColor_select) {
        super(backgroundImage, menuOptions, textColor, textColor_select);
    }

    public abstract void select(int index);

    public void render(Graphics g) {
        super.render(g);

        Font menuFont = getMenuFont();
        String[] menuOptions = getMenuOptions();
        int currentIndex = getCurrentIndex();
        Color textColor = getTextColor();
        Color textColor_select = getTextColor_select();

        if (menuFont != null)
            g.setFont(menuFont);

        for (int i = 0; i < menuOptions.length; i++){
            if (i == currentIndex)
                g.setColor(textColor_select);
            else
                g.setColor(textColor);

            g.drawString(menuOptions[i], (me.volition.Window.WINDOW_WIDTH / 2) - (g.getFontMetrics().stringWidth(menuOptions[i]) / 2), me.volition.Window.WINDOW_HEIGHT / 2 + 32 * i);
        }
    }
}
