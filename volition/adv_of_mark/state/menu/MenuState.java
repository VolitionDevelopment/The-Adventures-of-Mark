package volition.adv_of_mark.state.menu;

import volition.adv_of_mark.Window;
import volition.adv_of_mark.state.State;
import volition.adv_of_mark.util.AudioManager;
import volition.adv_of_mark.util.FontManager;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/3/16.
 */
public abstract class MenuState implements State {

    private Color textColor, textColor_select;
    private String[] menuOptions;
    private int currentIndex;
    private boolean hasSelected;
    private Font menuFont, menuFont_select;
    private BufferedImage backgroundImage;


    public MenuState(BufferedImage backgroundImage, String[]menuOptions, Color textColor, Color textColor_select){
        this.backgroundImage = backgroundImage;
        this.menuOptions = menuOptions;
        this.textColor = textColor;
        this.textColor_select = textColor_select;

        menuFont = FontManager.getSans(20);
        menuFont_select = menuFont.deriveFont(menuFont.getSize() + 7f);
    }

    public Font getMenuFont(){
        return menuFont;
    }

    public Font getMenuFont_select(){
        return menuFont_select;
    }

    public int getCurrentIndex(){
        return currentIndex;
    }

    public String[] getMenuOptions(){
        return menuOptions;
    }

    public Color getTextColor() {
        return textColor;
    }

    public Color getTextColor_select() {
        return textColor_select;
    }

    public void onKeyPress(){
        AudioManager.getInstance().playSound("/volition/adv_of_mark/assets/sound/effects/menu_blip.wav");
    }

    @Override
    public void init() {
        currentIndex = 0;
        hasSelected = false;
    }

    @Override
    public void update(double delta) {
        if (currentIndex > menuOptions.length - 1)
            currentIndex = 0;
        else if (currentIndex < 0)
            currentIndex = menuOptions.length - 1;

        if (hasSelected)
            select(currentIndex);
    }

    @Override
    public void keyPressed(int k) {

        if (k == KeyEvent.VK_S || k == KeyEvent.VK_D) {
            currentIndex++;
            onKeyPress();
        } else if (k == KeyEvent.VK_W || k == KeyEvent.VK_A) {
            currentIndex--;
            onKeyPress();
        }

        else if (k == KeyEvent.VK_ENTER) {
            hasSelected = true;
            AudioManager.getInstance().playSound("/volition/adv_of_mark/assets/sound/effects/menu_select.wav");
        }

    }

    @Override
    public void keyReleased(int k) {}

    @Override
    public void render(Graphics2D g){
        g.drawImage(backgroundImage, 0, 0, Window.WINDOW_WIDTH, Window.WINDOW_HEIGHT, null);
    }

    public abstract void select(int index);


}
