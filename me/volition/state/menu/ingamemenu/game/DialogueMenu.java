package me.volition.state.menu.ingamemenu.game;

import me.volition.component.Textbox;
import me.volition.entity.Entity;
import me.volition.state.menu.ingamemenu.InGameMenu;
import me.volition.util.GameManager;

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
    public void render(Graphics g) {
        textbox.render(g);
    }
}
