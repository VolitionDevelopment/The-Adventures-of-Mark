package volition.adv_of_mark.state.menu.ingamemenu.game;

import volition.adv_of_mark.component.Textbox;
import volition.adv_of_mark.mapObject.entity.Entity;
import volition.adv_of_mark.state.menu.ingamemenu.InGameMenu;
import volition.adv_of_mark.util.GameManager;

import java.awt.*;

/**
 * Created by mccloskeybr on 2/14/2016.
 */
public class DialogueMenu extends InGameMenu {

    private Textbox textbox;

    public DialogueMenu(Entity entity, String text) {
        super(new String[]{"Continue"});
        textbox = new Textbox(text, entity);
    }

    @Override
    public void select(int currentIndex) {
        if (currentIndex == 0)
            GameManager.getInstance().getGameState().setInGameMenu(null);
    }

    @Override
    public void render(Graphics2D g) {
        textbox.render(g);
    }
}
