package volition.adv_of_mark.state.menu.impl;

import volition.adv_of_mark.state.StateManager;
import volition.adv_of_mark.state.game.GameState;
import volition.adv_of_mark.state.menu.CenterTextMenu;
import volition.adv_of_mark.state.menu.impl.MainMenu;
import volition.adv_of_mark.state.menu.ingamemenu.InGameMenu;
import volition.adv_of_mark.util.FontManager;
import volition.adv_of_mark.util.ImageManager;
import volition.adv_of_mark.util.RenderUtils;

import java.awt.*;

/**
 * Created by mccloskeybr on 4/13/16.
 */
public class DeathMenu extends CenterTextMenu{

    public DeathMenu() {
        super(new ImageManager().loadImage("/volition/adv_of_mark/assets/image/menus/mainmenu.png"), new String[]{"- MAIN MENU -"}, Color.WHITE, Color.RED);
    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            StateManager.setCurrentState(new MainMenu());
    }

    @Override
    public void render(Graphics2D g) {
        super.render(g);

        g.setFont(FontManager.getSans(24));
        RenderUtils.drawCenteredText(g, "YOU DIED.", 80, 72);

    }

}
