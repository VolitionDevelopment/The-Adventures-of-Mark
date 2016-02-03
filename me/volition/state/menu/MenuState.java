package me.volition.state.menu;

import java.awt.event.KeyEvent;
import me.volition.state.State;
import me.volition.Window;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public abstract class MenuState implements State {

    private Color textColor, textColor_select;
    private String[] menuOptions;
    private int currentIndex;
    private boolean hasSelected;
    private BufferedImage backgroundImage;
    private Font menuFont;

    public MenuState(BufferedImage backgroundImage, String[]menuOptions, Color textColor, Color textColor_select){
        this.backgroundImage = backgroundImage;
        this.menuOptions = menuOptions;
        this.textColor = textColor;
        this.textColor_select = textColor_select;
    }

    public MenuState(BufferedImage backgroundImage, String[]menuOptions, Font menuFont, Color textColor, Color textColor_select){
        this.backgroundImage = backgroundImage;
        this.menuOptions = menuOptions;
        this.menuFont = menuFont;
        this.textColor = textColor;
        this.textColor_select = textColor_select;
    }

    @Override
    public void init() {
        currentIndex = 0;
        hasSelected = false;
    }

    @Override
    public void update() {
        if (currentIndex > menuOptions.length - 1)
            currentIndex = 0;
        else if (currentIndex < 0)
            currentIndex = menuOptions.length - 1;

        if (hasSelected)
            select(currentIndex);
    }

    @Override
    public void render(Graphics g) {
        g.drawImage(backgroundImage, 0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT, null);

        if (menuFont != null)
            g.setFont(menuFont);

        for (int i = 0; i < menuOptions.length; i++){
            if (i == currentIndex)
                g.setColor(textColor_select);
            else
                g.setColor(textColor);

            g.drawString(menuOptions[i], (Window.WINDOW_WIDTH / 2) - (g.getFontMetrics().stringWidth(menuOptions[i])), Window.WINDOW_HEIGHT / 2 + 32 * i);
        }
    }

    @Override
    public void keyPressed(int k) {
        if (k == KeyEvent.VK_S)
            currentIndex++;
        else if (k == KeyEvent.VK_W)
            currentIndex--;

        else if (k == KeyEvent.VK_ENTER)
            hasSelected = true;
    }

    @Override
    public void keyReleased(int k) {

    }

    public abstract void select(int index);

}
