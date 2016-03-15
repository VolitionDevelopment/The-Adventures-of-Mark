package volition.adv_of_mark.state.menu;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by mccloskeybr on 2/12/16.
 */
public abstract class LeftTextMenu extends MenuState {

    public LeftTextMenu(BufferedImage backgroundImage, String[] menuOptions, Color textColor, Color textColor_select) {
        super(backgroundImage, menuOptions, textColor, textColor_select);
    }

    public abstract void select(int index);

    public void render(Graphics2D g){
        super.render(g);

        String[] menuOptions = getMenuOptions();
        int currentIndex = getCurrentIndex();
        Color textColor = getTextColor();
        Color textColor_select = getTextColor_select();

        for (int i = 0; i < menuOptions.length; i++){
            if (i == currentIndex) {
                g.setColor(textColor_select);
                g.setFont(getMenuFont_select());

            } else {
                g.setColor(textColor);
                g.setFont(getMenuFont());
            }

            g.drawString(menuOptions[i], 20, 50 + i * 30);
        }
    }
}
