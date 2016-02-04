package me.volition.state.menu;

import me.volition.Window;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/4/16.
 */
public abstract class BottomTextMenu extends MenuState {

    public BottomTextMenu(BufferedImage backgroundImage, String[] menuOptions, Color textColor, Color textColor_select) {
        super(backgroundImage, menuOptions, textColor, textColor_select);
    }

    public BottomTextMenu(BufferedImage backgroundImage, String[] menuOptions, Font font, Color textColor, Color textColor_select) {
        super(backgroundImage, menuOptions, font, textColor, textColor_select);
    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        Font menuFont = getMenuFont();
        String[] menuOptions = getMenuOptions();
        int currentIndex = getCurrentIndex();
        Color textColor = getTextColor();
        Color textColor_select = getTextColor_select();

        if (menuFont != null)
            g.setFont(menuFont);

        for (int i = 0; i < menuOptions.length; i++) {
            if (i == currentIndex)
                g.setColor(textColor_select);
            else
                g.setColor(textColor);

            g.drawString(menuOptions[i], 100 + 100 * i, Window.WINDOW_HEIGHT - 75);
        }

    }

    @Override
    public abstract void select(int index);
}
